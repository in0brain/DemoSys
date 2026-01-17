package com.example.demosys.domain.auth.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUser {
    private Long id;

    private String username;
    private String displayName;

    private String userType;
    private Long orgId;

    private Integer status;           // 1启用/0禁用（你自己定）
    private String authProvider;      // LOCAL/SSO
    private String passwordHash;      // BCrypt hash
    private String passwordAlgo;      // BCRYPT
    private LocalDateTime passwordUpdatedAt;

    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
