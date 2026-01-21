package com.example.demosys.domain.auth.service.impl;

import com.example.demosys.domain.auth.dto.MeDisplayDTO;
import com.example.demosys.domain.auth.dto.MeProfileDTO;
import com.example.demosys.domain.auth.dto.MeStudentProfileDTO;
import com.example.demosys.domain.auth.dto.MeUserDTO;
import com.example.demosys.domain.auth.dto.MeUserProfileDTO;
import com.example.demosys.domain.auth.entity.SysOrg;
import com.example.demosys.domain.auth.entity.SysStudentProfile;
import com.example.demosys.domain.auth.entity.SysUser;
import com.example.demosys.domain.auth.entity.SysUserProfile;
import com.example.demosys.domain.auth.mapper.SysOrgMapper;
import com.example.demosys.domain.auth.mapper.SysRoleMapper;
import com.example.demosys.domain.auth.mapper.SysStudentProfileMapper;
import com.example.demosys.domain.auth.mapper.SysUserMapper;
import com.example.demosys.domain.auth.mapper.SysUserProfileMapper;
import com.example.demosys.domain.auth.service.MeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeServiceImpl implements MeService {

    // ✅ 新增：为了 getMeDisplay() 查库聚合（不影响 getProfile）
    private final SysUserMapper userMapper;
    private final SysUserProfileMapper userProfileMapper;
    private final SysStudentProfileMapper studentProfileMapper;
    private final SysOrgMapper orgMapper;
    private final SysRoleMapper roleMapper;

    public MeServiceImpl(SysUserMapper userMapper,
                         SysUserProfileMapper userProfileMapper,
                         SysStudentProfileMapper studentProfileMapper,
                         SysOrgMapper orgMapper,
                         SysRoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.userProfileMapper = userProfileMapper;
        this.studentProfileMapper = studentProfileMapper;
        this.orgMapper = orgMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public Object getProfile(Map<String, Object> params) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            // 你也可以抛 401，这里先返回空，避免 NPE
            return null;
        }

        Object principal = auth.getPrincipal();

        // 1) username：优先从 auth.getName()
        String username = auth.getName();

        // 2) displayName：如果 principal 里有 displayName 字段就取，否则用 username
        String displayName = extractStringField(principal, "displayName");
        if (displayName == null || displayName.isBlank()) displayName = username;

        // 3) roles：从 Spring Security authorities 提取 ROLE_XXX -> XXX
        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(Objects::nonNull)
                .map(s -> s.startsWith("ROLE_") ? s.substring(5) : s)
                .distinct()
                .collect(Collectors.toList());

        // ✅ MVP 兜底：如果 authorities 为空（你现在就是这种情况），先按用户名/显示名推断一个角色
        if (roles.isEmpty()) {
            // 先按 username
            if ("admin".equalsIgnoreCase(username)) roles = List.of("ADMIN");
                // 再按 displayName 关键字
            else if (displayName.contains("管理员")) roles = List.of("ADMIN");
            else if (displayName.contains("导师")) roles = List.of("TEACHER");
            else if (displayName.contains("学生")) roles = List.of("STUDENT");
            else if (displayName.contains("专家")) roles = List.of("EXPERT");
            else roles = List.of("STUDENT"); // 兜底给个默认，避免前端空 roles
        }

        // 4) permissions：第一轮可以先空
        List<String> permissions = Collections.emptyList();

        MeProfileDTO dto = new MeProfileDTO();
        dto.setUsername(username);
        dto.setDisplayName(displayName);
        dto.setRoles(roles);
        dto.setPermissions(permissions);

        return dto;
    }

    @Override
    public Object listPermissions(Map<String, Object> params) {
        // 第一轮可直接返回空数组，后续做 RBAC 再补
        return Collections.emptyList();
    }

    /**
     * 新接口：给个人中心展示用（数据库聚合）
     * - 优先查库：sys_user + sys_user_profile + (student? sys_student_profile) + org + roles
     * - permissions 第一迭代为空
     * - 如果查库失败：退回用 SecurityContext 的最小信息，避免前端崩
     */
    @Override
    public MeDisplayDTO getMeDisplay(Map<String, Object> params) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        String username = resolveUsername(auth);

        // ① 查库聚合
        MeDisplayDTO dto = buildDisplayFromDatabase(username, auth);
        if (dto != null) return dto;

        // ② 兜底：最小可展示结构
        return buildDisplayFallbackFromSecurityContext(auth);
    }

    private MeDisplayDTO buildDisplayFromDatabase(String username, Authentication auth) {
        SysUser user = userMapper.selectByUsername(username);
        if (user == null) return null;

        Long userId = user.getId();

        // roles：优先 DB（更权威）；如果 DB 为空再回退 authorities/兜底
        List<String> roles = roleMapper.selectRoleCodesByUserId(userId);
        if (roles == null || roles.isEmpty()) {
            roles = extractRolesFromAuth(auth);
        }

        // org：来自 sys_user.org_id
        SysOrg org = null;
        if (user.getOrgId() != null) {
            org = orgMapper.selectById(user.getOrgId());
        }

        // profile（通用）
        SysUserProfile profile = userProfileMapper.selectByUserId(userId);

        // displayName：优先 sys_user.displayName；再 principal；再 username
        String displayName = user.getDisplayName();
        if (displayName == null || displayName.isBlank()) {
            displayName = extractStringField(auth.getPrincipal(), "displayName");
        }
        if (displayName == null || displayName.isBlank()) {
            displayName = username;
        }

        // user dto
        MeUserDTO userDto = new MeUserDTO();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setDisplayName(displayName);
        userDto.setUserType(user.getUserType());
        userDto.setOrgId(user.getOrgId());
        userDto.setOrgName(org != null ? org.getOrgName() : null);

        // profile dto（避免 null）
        MeUserProfileDTO profileDto = new MeUserProfileDTO();
        if (profile != null) {
            profileDto.setPhone(profile.getPhone());
            profileDto.setEmail(profile.getEmail());
            profileDto.setTitle(profile.getTitle());
        }

        // student dto（仅 student 非空）
        MeStudentProfileDTO studentDto = null;
        if ("student".equalsIgnoreCase(user.getUserType())) {
            studentDto = buildStudentFromDatabase(userId);
        }

        MeDisplayDTO dto = new MeDisplayDTO();
        dto.setUser(userDto);
        dto.setRoles(roles);
        dto.setPermissions(Collections.emptyList()); // 第一迭代占位：[]
        dto.setProfile(profileDto);
        dto.setStudent(studentDto);

        return dto;
    }

    private MeStudentProfileDTO buildStudentFromDatabase(Long userId) {
        SysStudentProfile sp = studentProfileMapper.selectByUserId(userId);
        if (sp == null) return null;

        SysOrg college = null;
        SysOrg dept = null;

        if (sp.getCollegeOrgId() != null) {
            college = orgMapper.selectById(sp.getCollegeOrgId());
        }
        if (sp.getDeptOrgId() != null) {
            dept = orgMapper.selectById(sp.getDeptOrgId());
        }

        MeStudentProfileDTO dto = new MeStudentProfileDTO();
        dto.setStudentNo(sp.getStudentNo());
        dto.setCollegeName(college != null ? college.getOrgName() : null);
        dto.setDeptName(dept != null ? dept.getOrgName() : null);
        dto.setMajorName(sp.getMajorName());
        dto.setEnrollmentYear(sp.getEnrollmentYear());
        dto.setDegreeLevel(sp.getDegreeLevel());
        dto.setStudentStatus(sp.getStudentStatus());
        return dto;
    }

    private MeDisplayDTO buildDisplayFallbackFromSecurityContext(Authentication auth) {
        Object principal = auth.getPrincipal();
        String username = auth.getName();

        String displayName = extractStringField(principal, "displayName");
        if (displayName == null || displayName.isBlank()) displayName = username;

        List<String> roles = extractRolesFromAuth(auth);

        MeUserDTO userDto = new MeUserDTO();
        userDto.setId(null);
        userDto.setUsername(username);
        userDto.setDisplayName(displayName);
        userDto.setUserType(null);
        userDto.setOrgId(null);
        userDto.setOrgName(null);

        MeDisplayDTO dto = new MeDisplayDTO();
        dto.setUser(userDto);
        dto.setRoles(roles);
        dto.setPermissions(Collections.emptyList());
        dto.setProfile(new MeUserProfileDTO());
        dto.setStudent(null);
        return dto;
    }

    /**
     * 复用你原来的 roles 提取 + 兜底逻辑（保持行为一致）
     */
    private List<String> extractRolesFromAuth(Authentication auth) {
        String username = auth.getName();
        String displayName = extractStringField(auth.getPrincipal(), "displayName");
        if (displayName == null || displayName.isBlank()) displayName = username;

        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(Objects::nonNull)
                .map(s -> s.startsWith("ROLE_") ? s.substring(5) : s)
                .distinct()
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            if ("admin".equalsIgnoreCase(username)) roles = List.of("ADMIN");
            else if (displayName.contains("管理员")) roles = List.of("ADMIN");
            else if (displayName.contains("导师")) roles = List.of("TEACHER");
            else if (displayName.contains("学生")) roles = List.of("STUDENT");
            else if (displayName.contains("专家")) roles = List.of("EXPERT");
            else roles = List.of("STUDENT");
        }
        return roles;
    }

    /**
     * 反射取 principal 的某个 String 字段（避免强依赖你的 principal 类型）
     */
    private String extractStringField(Object obj, String fieldName) {
        if (obj == null) return null;
        try {
            var f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            Object v = f.get(obj);
            return v == null ? null : String.valueOf(v);
        } catch (Exception ignored) {
            return null;
        }
    }

    private String resolveUsername(Authentication auth) {
        if (auth == null) return null;

        Object principal = auth.getPrincipal();

        // 1) 反射：尝试取 principal.username 字段（你的 UserPrincipal 正好有）
        String u = extractStringField(principal, "username");
        if (u != null && !u.isBlank()) return u;

        // 2) 若 principal 是 Spring Security UserDetails
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails ud) {
            if (ud.getUsername() != null && !ud.getUsername().isBlank()) return ud.getUsername();
        }

        // 3) 最后 fallback
        String name = auth.getName();
        return (name == null || name.isBlank()) ? null : name;
    }

}
