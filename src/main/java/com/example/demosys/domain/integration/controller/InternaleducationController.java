package com.example.demosys.domain.integration.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * InternaleducationController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/internal")
public class InternaleducationController {

    /**
     * 分组：3.3 学分统计与毕业资格（SR-EDU-3 / IR-EDU-1 / IR-EDU-3）
     * 描述：选课结果事件推送到学分统计（内部）
     * 角色：内部服务
     * 关联：IR-EDU-1
     */
    @PostMapping("/education/enrollment-events")
    public ApiResponse createEducationEnrollmentEvents(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

    /**
     * 描述：录取新生基础信息写入培养学生库（幂等）
     */
    @PostMapping("/education/students/import")
    public ApiResponse createEducationStudentsImport(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
