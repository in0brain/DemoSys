package com.example.demosys.domain.auth.dto;

import lombok.Data;

@Data
public class LocalLoginRequest {
    private String username;
    private String password;
}
