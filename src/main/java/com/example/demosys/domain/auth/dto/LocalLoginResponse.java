package com.example.demosys.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocalLoginResponse {
    private UserBriefDTO user;
    private String tokenType;   // "Bearer"
    private long expiresIn;     // seconds
    private String token;
}
