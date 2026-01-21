package com.example.demosys.domain.education.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * EnrollmentsRequest
 * DTO 占位：按接口字段补充属性/校验注解。
 */
@Data
public class EnrollmentsRequest {
    /** SR 最简：直接传课程明细（courseCode + credit） */
    private List<Item> courses;

    @Data
    public static class Item {
        private Long courseId;        // 可空
        private String courseCode;    // 必填
        private String courseName;    // 可空（前端可带）
        private BigDecimal credit;    // 必填
    }
}
