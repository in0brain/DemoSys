package com.example.demosys.domain.auth.service.impl;

import com.example.demosys.common.security.JwtTokenService;
import com.example.demosys.domain.auth.dto.LocalLoginRequest;
import com.example.demosys.domain.auth.dto.LocalLoginResponse;
import com.example.demosys.domain.auth.dto.UserBriefDTO;
import com.example.demosys.domain.auth.entity.SysUser;
import com.example.demosys.domain.auth.mapper.SysRoleMapper;
import com.example.demosys.domain.auth.mapper.SysUserMapper;
import com.example.demosys.domain.auth.service.LocalAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    private final SysUserMapper userMapper;
    private final SysRoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    public LocalAuthServiceImpl(SysUserMapper userMapper,
                                SysRoleMapper roleMapper,
                                PasswordEncoder passwordEncoder,
                                JwtTokenService tokenService) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public LocalLoginResponse login(LocalLoginRequest req) {
        if (req == null || isBlank(req.getUsername()) || isBlank(req.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "请输入账号和密码");
        }

        // 1) 标准化 username（避免前端多空格导致查不到）
        final String username = req.getUsername().trim();

        // 2) password 先不动，联调阶段观测真实输入（是否有首尾空格）
        final String rawPassword = req.getPassword();
        final boolean pwHasEdgeSpaces = rawPassword != null && !rawPassword.equals(rawPassword.trim());

        log.info("[LOGIN] username='{}' uLen={} pLen={} pwEdgeSpaces={}",
                username,
                username.length(),
                rawPassword == null ? -1 : rawPassword.length(),
                pwHasEdgeSpaces
        );

        // 3) 查用户：如果这里为 null，就是 username/字段/数据的问题
        SysUser user = userMapper.selectByUsername(username);
        if (user == null) {
            log.warn("[LOGIN] user NOT found by username='{}'", username);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "账号或密码错误");
        }

        // 4) 打印用户关键状态 + hash 前缀（不泄露全文）
        log.info("[LOGIN] userFound id={} status={} provider={} hashPrefix={} hashLen={}",
                user.getId(),
                user.getStatus(),
                user.getAuthProvider(),
                prefix(user.getPasswordHash(), 7), // 预期 $2a$10$
                user.getPasswordHash() == null ? -1 : user.getPasswordHash().length()
        );

        // status 约定：1 启用；0/其他 禁用
        if (user.getStatus() != null && user.getStatus() != 1) {
            log.warn("[LOGIN] user disabled id={} username='{}' status={}",
                    user.getId(), username, user.getStatus());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "账号已被禁用");
        }

        // provider 约定：local 才允许密码登录
        if (user.getAuthProvider() != null && !"local".equalsIgnoreCase(user.getAuthProvider())) {
            log.warn("[LOGIN] provider not local id={} username='{}' provider={}",
                    user.getId(), username, user.getAuthProvider());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "该账号不支持本地密码登录");
        }

        // 5) 密码校验：分别测原始输入 & trim 后输入，快速判断是不是“空格导致”
        String dbHash = user.getPasswordHash();
        if (isBlank(dbHash)) {
            log.error("[LOGIN] db password_hash is blank! id={} username='{}'", user.getId(), username);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "账号或密码错误");
        }

        boolean matchRaw = passwordEncoder.matches(rawPassword, dbHash);
        boolean matchTrim = rawPassword != null && passwordEncoder.matches(rawPassword.trim(), dbHash);

        log.info("[LOGIN] passwordMatches raw={} trim={} (if trim=true => 前端/用户输入有空格)",
                matchRaw, matchTrim);

        // 兼容：允许首尾空格（联调阶段实用）；上线可改成严格 matchRaw
        if (!matchRaw && !matchTrim) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "账号或密码错误");
        }
        if (!matchRaw && matchTrim) {
            log.warn("[LOGIN] password matched only after trim. username='{}' (建议前端trim输入)", username);
        }

        // 6) 查角色（✅使用 List 放入 JWT，避免 Set 类型不稳定）
        List<String> roles = roleMapper.selectRoleCodesByUserId(user.getId());
        if (roles == null) roles = List.of();

        // 7) 生成 JWT（✅roles 直接写入 claims）
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("displayName", user.getDisplayName());
        claims.put("roles", roles);

        String token = tokenService.createToken(user.getUsername(), claims);

        // 8) 记录 last_login_at（失败也不影响登录）
        try {
            userMapper.updateLastLoginAt(user.getId());
        } catch (Exception ignored) {
        }

        // 9) 返回
        return new LocalLoginResponse(
                new UserBriefDTO(user.getUsername(), user.getDisplayName()),
                "Bearer",
                tokenService.getTtlSeconds(),
                token
        );
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private String prefix(String s, int n) {
        if (s == null) return "null";
        return s.substring(0, Math.min(n, s.length()));
    }
}
