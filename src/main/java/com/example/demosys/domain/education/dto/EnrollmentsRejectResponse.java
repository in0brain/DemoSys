package com.example.demosys.domain.education.dto;

import lombok.Data;

/**
 * EnrollmentsRejectResponse
 * DTO 占位：按接口字段补充属性/校验注解。
 */
@Data
public class EnrollmentsRejectResponse {
    private Long enrollmentId;
    private EnrollmentStatus status;      // REJECTED
    private ApprovalNode currentNode; // DONE
}