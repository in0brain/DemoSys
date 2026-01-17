package com.example.demosys.common.security;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * PermissionEvaluator（最小版）
 * 先用角色判断，后续可扩展成 RBAC/ABAC。
 */
@Component
public class PermissionEvaluator {

    public boolean hasRole(UserPrincipal p, String role) {
        if (p == null || p.getRoles() == null) return false;
        Set<String> roles = p.getRoles();
        return roles.contains(role);
    }
}
