package com.example.demosys.domain.integration.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * InternalnotificationController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/internal")
public class InternalnotificationController {

    /**
     * 分组：通知中心
     * 描述：内部：发送通知（支持邮件/短信策略）
     * 角色：内部服务
     * 关联：IR-NOTI-1
     */
    @PostMapping("/notifications/send")
    public ApiResponse createNotificationsSend(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
