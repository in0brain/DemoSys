package com.example.demosys.domain.education.service;

import com.example.demosys.domain.education.dto.EnrollmentsApproveRequest;
import com.example.demosys.domain.education.dto.EnrollmentsCancelRequest;
import com.example.demosys.domain.education.dto.EnrollmentsRejectRequest;
import com.example.demosys.domain.education.dto.EnrollmentsRequest;

/**
 * EnrollmentService
 * 自动生成：培养模块 Service（资源：enrollments）。
 */
public interface EnrollmentService {

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：我的选课申请/课表
     * 角色：学生
     * 关联：SR-EDU-2
     */
    Object listEnrollments(java.util.Map<String, Object> params);

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：提交选课申请（课程清单）
     * 角色：学生
     * 关联：SR-EDU-2
     */
    Object createEnrollments(EnrollmentsRequest request);

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：审批通过（导师/学院）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-2
     */
    Object createEnrollmentsByIdApprove(EnrollmentsApproveRequest request);

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：撤回申请（规则：未审批前）
     * 角色：学生
     * 关联：SR-EDU-2
     */
    Object createEnrollmentsByIdCancel(EnrollmentsCancelRequest request);

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：退回（含原因）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-2
     */
    Object createEnrollmentsByIdReject(EnrollmentsRejectRequest request);

}
