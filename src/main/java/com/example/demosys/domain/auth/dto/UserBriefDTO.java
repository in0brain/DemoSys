package com.example.demosys.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBriefDTO {
    private String username;
    private String displayName;
}
