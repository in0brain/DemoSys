package com.example.demosys.domain.education.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * EnrollmentsResponse
 * DTO 占位：按接口字段补充属性/校验注解。
 */
@Data
public class EnrollmentsResponse {
    private Long id;
    private String enrollmentNo;

    private Long studentId;
    private String studentName;

    private EnrollmentStatus status;       // SUBMITTED/...
    private ApprovalNode currentNode;  // ADVISOR/COLLEGE/DONE

    private String submitTime;
    private String finishTime;

    private List<CourseItem> courses;
    private List<AuditItem> audits;

    @Data
    public static class CourseItem {
        private Long courseId;
        private String courseCode;
        private String courseName;
        private BigDecimal credit;
    }

    @Data
    public static class AuditItem {
        private ApprovalNode node;          // ADVISOR/COLLEGE
        private String auditorRole;   // TEACHER/ADMIN
        private String auditorName;
        private ApprovalAction action;        // APPROVE/REJECT
        private String actionTime;
    }
}
