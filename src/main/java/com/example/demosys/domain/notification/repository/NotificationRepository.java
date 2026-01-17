package com.example.demosys.domain.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demosys.domain.notification.entity.NotificationEntity;

/**
 * NotificationRepository
 * JPA 仓储占位：如你只用 MyBatis/消息队列，可删除该类。
 */
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}
