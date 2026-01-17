package com.example.demosys.domain.jobs.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * JobEntity
 * 调度任务实体占位类：可映射到 jobs 表（type、cron、status、payload、lastRunAt...）。
 */
@Data
@Entity
@Table(name = "job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 任务类型：例如 SYNC_ENROLLMENT / SEND_NOTIFICATION / EXPORT_REPORT ... */
    private String jobType;

    /** cron 表达式（若是一次性任务可为空） */
    private String cron;

    /** 状态：PENDING/RUNNING/SUCCESS/FAILED... */
    private String status;

    /** 最近一次运行时间 */
    private LocalDateTime lastRunAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
