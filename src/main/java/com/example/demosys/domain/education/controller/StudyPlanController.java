package com.example.demosys.domain.education.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * StudyPlanController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/education")
public class StudyPlanController {

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：我的培养计划列表（含版本/状态）
     * 角色：学生
     * 关联：SR-EDU-1
     */
    @GetMapping("/study-plans")
    public ApiResponse listStudyPlans(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：新建培养计划（草稿）
     * 角色：学生
     * 关联：SR-EDU-1
     */
    @PostMapping("/study-plans")
    public ApiResponse createStudyPlans(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：编辑培养计划
     * 角色：学生
     * 关联：SR-EDU-1
     */
    @PutMapping("/study-plans/{planId}")
    public ApiResponse updateStudyPlansBy(@PathVariable("planId") String planId, @RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：审批通过（导师/学院）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-1
     */
    @PostMapping("/study-plans/{planId}/approve")
    public ApiResponse createStudyPlansByApprove(@PathVariable("planId") String planId, @RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：退回修改（含意见）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-1
     */
    @PostMapping("/study-plans/{planId}/reject")
    public ApiResponse createStudyPlansByReject(@PathVariable("planId") String planId, @RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：提交培养计划审核
     * 角色：学生
     * 关联：SR-EDU-1
     */
    @PostMapping("/study-plans/{planId}/submit")
    public ApiResponse createStudyPlansBySubmit(@PathVariable("planId") String planId, @RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
