package com.example.demosys.domain.auth.controller;

import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.auth.service.MeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/me")
public class MeController {

    private final MeService meService;

    public MeController(MeService meService) {
        this.meService = meService;
    }

    @GetMapping("")
    public ApiResponse me(@RequestParam(required = false) Map<String, Object> params) {
        return ApiResponse.ok(meService.getProfile(params));
    }

    @GetMapping("/permissions")
    public ApiResponse listPermissions(@RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) Integer pageSize,
                                       @RequestParam(required = false) Map<String, Object> filters) {
        return ApiResponse.ok(Collections.emptyList());
    }
    @GetMapping("/debug/auth")
    public Object debugAuth() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return Map.of(
                "name", a.getName(),
                "principalClass", a.getPrincipal().getClass().getName(),
                "authorities", a.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        );
    }

    // TODO: 第二迭代实现：仅允许更新 phone/email/title/avatarUrl 等白名单字段，记录审计日志
    @PutMapping("/update/profile")
    public ApiResponse updateProfile(@RequestBody Map<String, Object> req) {
        return ApiResponse.ok();
    }

    @GetMapping("/display/profile")
    public ApiResponse getDisplayProfile(@RequestParam(required = false) Map<String, Object> params) {
        return ApiResponse.ok(meService.getMeDisplay(params));
    }
}
