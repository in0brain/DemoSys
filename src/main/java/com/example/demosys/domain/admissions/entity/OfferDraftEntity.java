package com.example.demosys.domain.admissions.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * OfferDraftEntity
 * 实体占位类：请按招生模块表结构补充字段。
 */
@Data
@Entity
@Table(name = "offerdraftentity")
public class OfferDraftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
