package com.example.demosys.common.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements Serializable {
    private String username;
    private String displayName;

    // 先占位，后续接 DB 再补
    private Set<String> roles;
    private Set<String> permissions;
}
