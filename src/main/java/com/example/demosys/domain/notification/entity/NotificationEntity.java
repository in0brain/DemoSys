package com.example.demosys.domain.notification.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * NotificationEntity
 * 通知域实体占位：可映射模板、消息、发送记录等表。
 */
@Data
@Entity
@Table(name = "notificationentity")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
