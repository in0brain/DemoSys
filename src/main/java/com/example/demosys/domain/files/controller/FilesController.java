package com.example.demosys.domain.files.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * FilesController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/files")
public class FilesController {

    /**
     * 分组：文件服务
     * 描述：上传文件（返回 fileId）
     * 角色：登录用户
     * 关联：IR-FILE-1
     */
    @PostMapping("/")
    public ApiResponse createRoot(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
