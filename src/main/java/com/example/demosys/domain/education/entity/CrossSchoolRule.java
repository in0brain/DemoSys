package com.example.demosys.domain.education.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * CrossSchoolRule
 * 实体占位：请根据表结构补充字段/索引/约束。
 */
@Data
@Entity
@Table(name = "edu_crossschoolrule")
public class CrossSchoolRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
