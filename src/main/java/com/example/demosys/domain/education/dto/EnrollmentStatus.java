package com.example.demosys.domain.education.dto;

public enum EnrollmentStatus {
    SUBMITTED,        // 学生已提交，待导师
    ADVISOR_APPROVED, // 导师已通过，待学院
    COLLEGE_APPROVED, // 学院终审通过（最终态）
    REJECTED          // 任意节点驳回（最终态）
}
