package com.example.demosys.domain.education.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * ProgramController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/education")
public class ProgramController {

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：培养方案详情（学分要求、课程分类）
     * 角色：登录用户
     * 关联：SR-EDU-1
     */
    @GetMapping("/programs/{programId}")
    public ApiResponse getProgramsBy(@PathVariable("programId") String programId) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
