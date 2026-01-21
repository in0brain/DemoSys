package com.example.demosys.domain.education.controller;

import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.common.exception.BizException;
import com.example.demosys.domain.education.dto.*;
import com.example.demosys.domain.education.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationEnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：我的选课申请/课表（SR：这里先实现“我的选课申请列表”）
     * 角色：学生
     * 关联：SR-EDU-2
     *
     * 说明（SR极简）：
     * - page/pageSize 暂不处理
     * - filters 暂不处理
     */
    @GetMapping("/enrollments")
    public ApiResponse listEnrollments(@RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) Integer pageSize,
                                       @RequestParam(required = false) Map<String, String> filters) {
        List<EnrollmentsResponse> list = enrollmentService.listMine();
        return ApiResponse.ok(list);
    }

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：提交选课申请（课程清单）
     * 角色：学生
     * 关联：SR-EDU-2
     */
    @PostMapping("/enrollments")
    public ApiResponse createEnrollments(@RequestBody EnrollmentsRequest request) {
        return ApiResponse.ok(enrollmentService.submit(request));
    }

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：审批通过（导师/学院）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-2
     *
     * 说明（SR极简）：
     * - Service 内部根据 enrollment.currentNode + status 自动判断是导师还是学院审批
     * - enrollId 从 PathVariable 传入，request 体里可以为空
     */
    @PostMapping("/enrollments/{enrollId}/approve")
    public ApiResponse createEnrollmentsByApprove(@PathVariable("enrollId") String enrollId,
                                                  @RequestBody(required = false) EnrollmentsApproveRequest request) {
        Long id = parseId(enrollId);

        EnrollmentsApproveRequest req = (request == null) ? new EnrollmentsApproveRequest() : request;
        req.setEnrollmentId(id);

        return ApiResponse.ok(enrollmentService.approve(req));
    }

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：撤回申请（规则：未审批前）
     * 角色：学生
     * 关联：SR-EDU-2
     *
     * SR范围说明：你在需求里明确“不做撤回/修改”，所以这里先保留接口但返回不支持。
     * 后续要做撤回，再补：只允许 SUBMITTED 且 currentNode=ADVISOR 的撤回。
     */
    @PostMapping("/enrollments/{enrollId}/cancel")
    public ApiResponse createEnrollmentsByCancel(@PathVariable("enrollId") String enrollId,
                                                 @RequestBody(required = false) Object request) {
        throw new BizException("SR-EDU-2 极简闭环暂不支持撤回申请");
    }

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：退回（含原因）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-2
     *
     * 说明：
     * - SR极简：不做原因流转（DTO里如果有 reason 字段也先忽略）
     */
    @PostMapping("/enrollments/{enrollId}/reject")
    public ApiResponse createEnrollmentsByReject(@PathVariable("enrollId") String enrollId,
                                                 @RequestBody(required = false) EnrollmentsRejectRequest request) {
        Long id = parseId(enrollId);

        EnrollmentsRejectRequest req = (request == null) ? new EnrollmentsRejectRequest() : request;
        req.setEnrollmentId(id);

        return ApiResponse.ok(enrollmentService.reject(req));
    }

    @GetMapping("/enrollments/todos")
    public ApiResponse todos(@RequestParam("node") String node) {
        return ApiResponse.ok(enrollmentService.listTodos(node));
    }


    // =========================
    // helpers
    // =========================
    private Long parseId(String raw) {
        if (raw == null || raw.isBlank()) throw new BizException("enrollId 不能为空");
        try {
            return Long.valueOf(raw);
        } catch (Exception e) {
            throw new BizException("enrollId 必须是数字：" + raw);
        }
    }

}
