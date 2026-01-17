package com.example.demosys.domain.defense.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * MeEntity
 * 实体占位类：请按权限/用户/令牌等表结构补充字段。
 */
@Data
@Entity
@Table(name = "meentity")
public class DefenseMeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
