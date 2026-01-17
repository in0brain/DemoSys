package com.example.demosys.domain.admissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demosys.domain.admissions.entity.EnrollmentSyncEntity;

/**
 * EnrollmentSyncRepository
 * JPA 仓储占位：如你只用 MyBatis，可删除该类。
 */
public interface EnrollmentSyncRepository extends JpaRepository<EnrollmentSyncEntity, Long> {

}
