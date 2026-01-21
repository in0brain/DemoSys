package com.example.demosys.domain.education.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "edu_enrollment_item",
        indexes = @Index(name = "idx_enr_item_enrollment", columnList = "enrollment_id"))
public class EnrollmentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrollment_id", nullable = false)
    private Long enrollmentId;

    /** 课程表对接后可用 courseId；SR 阶段可为空 */
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_code", length = 64, nullable = false)
    private String courseCode;

    @Column(name = "course_name", length = 128)
    private String courseName;

    @Column(name = "credit", precision = 5, scale = 1, nullable = false)
    private BigDecimal credit;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // getters/setters ...
}
