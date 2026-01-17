package com.example.demosys.domain.admissions.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * OfferPublicationEntity
 * 实体占位类：请按招生模块表结构补充字段。
 */
@Data
@Entity
@Table(name = "offerpublicationentity")
public class OfferPublicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
