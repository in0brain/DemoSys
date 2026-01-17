package com.example.demosys.domain.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demosys.domain.jobs.entity.JobEntity;

/**
 * JobRepository
 * JPA 仓储占位：如你使用 Quartz/XXL-Job 等外部调度组件，可删除该类。
 */
public interface JobRepository extends JpaRepository<JobEntity, Long> {

}
