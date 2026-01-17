package com.example.demosys.domain.integration.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * UserssyncController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("")
public class UserssyncController {

    /**
     * 描述：用户与角色信息同步（定时/事件触发）
     */
    @PostMapping("/users/sync")
    public ApiResponse createSync(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
