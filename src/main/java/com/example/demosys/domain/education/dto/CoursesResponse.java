package com.example.demosys.domain.education.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * CoursesResponse
 * DTO 占位：按接口字段补充属性/校验注解。
 */
@Data
public class CoursesResponse {
    private List<CourseItem> items;

    @Data
    public static class CourseItem {
        private Long id;
        private String courseCode;
        private String courseName;
        private BigDecimal credit;
    }
}
