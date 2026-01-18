package com.example.demosys.domain.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class MeProfileDTO {
    private Long id;
    private String username;
    private String displayName;
    private String userType;
    private Long orgId;
    private List<String> roles;
    private List<String> permissions;
}
