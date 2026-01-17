package com.example.demosys.domain.defense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demosys.domain.auth.entity.MeEntity;

/**
 * MeRepository
 * JPA 仓储占位：如你只用 MyBatis，可删除该类。
 */
public interface DefenseMeRepository extends JpaRepository<MeEntity, Long> {

}
