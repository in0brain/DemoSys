package com.example.demosys.domain.admissions.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * EnrollmentController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/admissions")
public class AdmissionsEnrollmentController {

    /**
     * 分组：2.3 录取新生同步（SR-ADM-3 / IR-ADM-1 / IR-ADM-2）
     * 描述：将已录取新生同步到培养学生库（批处理）
     * 角色：招生管理员
     * 关联：SR-ADM-3, IR-ADM-1, IR-ADM-2
     * 备注：内部接口（模块间）；可实现为 REST 或 MQ
     */
    @PostMapping("/enrollments/sync")
    public ApiResponse createEnrollmentsSync(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：2.3 录取新生同步（SR-ADM-3 / IR-ADM-1 / IR-ADM-2）
     * 描述：同步对账状态/差异清单
     * 角色：招生管理员
     * 关联：SR-ADM-3, IR-ADM-1, IR-ADM-2
     */
    @GetMapping("/enrollments/sync/status")
    public ApiResponse listEnrollmentsSyncStatus(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
