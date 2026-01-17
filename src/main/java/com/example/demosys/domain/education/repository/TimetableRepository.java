package com.example.demosys.domain.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demosys.domain.education.entity.Timetable;

/**
 * TimetableRepository
 * JPA 仓储占位：如仅使用 MyBatis，可删除。
 */
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
