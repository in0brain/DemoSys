package com.example.demosys.infra.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JpaConfig（占位）
 * 若你使用 Spring Data JPA，可开启审计（createdAt/updatedAt）。
 * 不使用 JPA 可删除该类。
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // TODO: add auditing/hibernate naming strategy if needed
}
