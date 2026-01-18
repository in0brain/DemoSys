package com.example.demosys.domain.auth.service.impl;

import com.example.demosys.domain.auth.dto.MeProfileDTO;
import com.example.demosys.domain.auth.service.MeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeServiceImpl implements MeService {

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
}
