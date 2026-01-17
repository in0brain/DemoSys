package com.example.demosys.domain.education.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * ApprovalController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/education")
public class ApprovalController {

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：待我审批的选课申请
     * 角色：导师/培养管理员
     * 关联：SR-EDU-2
     */
    @GetMapping("/approvals/enrollments/pending")
    public ApiResponse listApprovalsEnrollmentsPending(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：待我审批的培养计划
     * 角色：导师/培养管理员
     * 关联：SR-EDU-1
     */
    @GetMapping("/approvals/study-plans/pending")
    public ApiResponse listApprovalsStudyPlansPending(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
