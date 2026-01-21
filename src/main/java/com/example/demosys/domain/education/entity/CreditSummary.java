package com.example.demosys.domain.education.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CreditSummary
 * 实体占位：请根据表结构补充字段/索引/约束。
 */
@Data
@Entity
@Table(name = "edu_credit_summary",
        uniqueConstraints = @UniqueConstraint(name = "uk_credit_student", columnNames = "student_id"))
public class CreditSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "total_credit", precision = 6, scale = 1, nullable = false)
    private BigDecimal totalCredit;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // getters/setters ...
}
