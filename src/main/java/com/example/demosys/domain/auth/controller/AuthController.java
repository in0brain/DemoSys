package com.example.demosys.domain.auth.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * AuthController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * 描述：SSO 回调换取 token
     * 关联：IR-AUTH-1
     * 备注：网关也可做
     */
    @PostMapping("/sso/callback")
    public ApiResponse createSsoCallback(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 描述：获取统一认证跳转地址
     * 关联：IR-AUTH-1
     * 备注：web 前端用
     */
    @GetMapping("/sso/login-url")
    public ApiResponse listSsoLoginUrl(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }



}
