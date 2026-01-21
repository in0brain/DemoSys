package com.example.demosys.domain.education.entity;

import com.example.demosys.domain.education.dto.ApprovalAction;
import com.example.demosys.domain.education.dto.ApprovalNode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "edu_enrollment_audit",
        indexes = {
                @Index(name = "idx_enr_audit_enrollment", columnList = "enrollment_id"),
                @Index(name = "idx_enr_audit_auditor", columnList = "auditor_id")
        })
public class EnrollmentAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrollment_id", nullable = false)
    private Long enrollmentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "node", length = 32, nullable = false)
    private ApprovalNode node; // ADVISOR / COLLEGE

    @Column(name = "auditor_id", nullable = false)
    private Long auditorId;

    @Column(name = "auditor_name", length = 64, nullable = false)
    private String auditorName;

    @Column(name = "auditor_role", length = 32, nullable = false)
    private String auditorRole; // TEACHER / ADMIN（先字符串，后续可枚举）

    @Enumerated(EnumType.STRING)
    @Column(name = "action", length = 16, nullable = false)
    private ApprovalAction action;

    @Column(name = "action_time", nullable = false)
    private LocalDateTime actionTime;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // getters/setters ...
}
