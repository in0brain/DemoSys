package com.example.demosys.domain.auth.controller;

import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.auth.dto.LocalLoginRequest;
import com.example.demosys.domain.auth.service.LocalAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/local")
public class LocalAuthController {

    private final LocalAuthService localAuthService;

    public LocalAuthController(LocalAuthService localAuthService) {
        this.localAuthService = localAuthService;
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LocalLoginRequest req) {
        return ApiResponse.ok(localAuthService.login(req));
    }
}
