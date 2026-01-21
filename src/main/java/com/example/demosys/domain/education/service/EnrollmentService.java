package com.example.demosys.domain.education.service;

import com.example.demosys.domain.education.dto.*;
import java.util.List;

public interface EnrollmentService {

    /**
     * 学生提交选课申请（一次性提交）
     */
    EnrollmentsResponse submit(EnrollmentsRequest req);

    /**
     * 学生查看我的申请列表
     */
    List<EnrollmentsResponse> listMine();

    /**
     * 导师/学院待办列表
     * node: "ADVISOR" 或 "COLLEGE"
     */
    ApprovalsEnrollmentsResponse listTodos(String node);

    /**
     * 导师/学院审批通过
     */
    EnrollmentsApproveResponse approve(EnrollmentsApproveRequest req);

    /**
     * 导师/学院驳回
     */
    EnrollmentsRejectResponse reject(EnrollmentsRejectRequest req);

    /**
     * 查看申请详情（含课程明细 + 审批记录）
     */
    EnrollmentsResponse getDetail(Long enrollmentId);
}
