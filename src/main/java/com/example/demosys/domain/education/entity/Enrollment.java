package com.example.demosys.domain.education.entity;

import com.example.demosys.domain.education.dto.ApprovalNode;
import com.example.demosys.domain.education.dto.EnrollmentStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Enrollment
 * 实体占位：请根据表结构补充字段/索引/约束。
 */
@Data
@Entity
@Table(name = "edu_enrollment",
        indexes = {
                @Index(name = "idx_enr_student", columnList = "student_id"),
                @Index(name = "idx_enr_status", columnList = "status"),
                @Index(name = "idx_enr_node", columnList = "current_node")
        })
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 业务单号：可选，便于对外展示（也可以不用） */
    @Column(name = "enrollment_no", length = 64, unique = true, nullable = false)
    private String enrollmentNo;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "student_name", length = 64, nullable = false)
    private String studentName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 32, nullable = false)
    private EnrollmentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_node", length = 32, nullable = false)
    private ApprovalNode currentNode;

    @Column(name = "submit_time", nullable = false)
    private LocalDateTime submitTime;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // getters/setters ...
}
