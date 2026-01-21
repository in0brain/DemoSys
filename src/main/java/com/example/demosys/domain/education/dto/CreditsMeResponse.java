package com.example.demosys.domain.education.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * CreditsMeResponse
 * DTO 占位：按接口字段补充属性/校验注解。
 */
@Data
public class CreditsMeResponse {
    private Long studentId;
    private BigDecimal totalCredit;

    /** 已通过课程（学院终审通过） */
    private List<PassedCourse> passedCourses;

    @Data
    public static class PassedCourse {
        private String courseCode;
        private String courseName;
        private BigDecimal credit;
        private Long fromEnrollmentId;
        private String approvedTime;
    }
}
