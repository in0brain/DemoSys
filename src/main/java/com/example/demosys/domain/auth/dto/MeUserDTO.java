package com.example.demosys.domain.auth.dto;

import lombok.Data;

@Data
public class MeUserDTO {

    private Long id;

    private String username;

    private String displayName;

    private String userType;

    private Long orgId;

    private String orgName;
}
