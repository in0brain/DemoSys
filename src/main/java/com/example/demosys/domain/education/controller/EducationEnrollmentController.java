package com.example.demosys.domain.education.controller;

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
@RequestMapping("/education")
public class EducationEnrollmentController {

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：我的选课申请/课表
     * 角色：学生
     * 关联：SR-EDU-2
     */
    @GetMapping("/enrollments")
    public ApiResponse listEnrollments(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：提交选课申请（课程清单）
     * 角色：学生
     * 关联：SR-EDU-2
     */
    @PostMapping("/enrollments")
    public ApiResponse createEnrollments(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：审批通过（导师/学院）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-2
     */
    @PostMapping("/enrollments/{enrollId}/approve")
    public ApiResponse createEnrollmentsByApprove(@PathVariable("enrollId") String enrollId, @RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：撤回申请（规则：未审批前）
     * 角色：学生
     * 关联：SR-EDU-2
     */
    @PostMapping("/enrollments/{enrollId}/cancel")
    public ApiResponse createEnrollmentsByCancel(@PathVariable("enrollId") String enrollId, @RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：退回（含原因）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-2
     */
    @PostMapping("/enrollments/{enrollId}/reject")
    public ApiResponse createEnrollmentsByReject(@PathVariable("enrollId") String enrollId, @RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
