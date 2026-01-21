package com.example.demosys.domain.education.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * ApprovalsEnrollmentsResponse
 * DTO 导师/学院待办列表
 */
@Data

public class ApprovalsEnrollmentsResponse {
    private List<Item> items;

    @Data
    public static class Item {
        private Long id;
        private String enrollmentNo;

        private Long studentId;
        private String studentName;

        private EnrollmentStatus status;
        private ApprovalNode currentNode;
        private String submitTime;

        /** 方便列表展示 */
        private Integer courseCount;
        private BigDecimal totalCredit;

        /** 可选：展开详情用 */
        private List<EnrollmentsResponse.CourseItem> courses;
    }
}
