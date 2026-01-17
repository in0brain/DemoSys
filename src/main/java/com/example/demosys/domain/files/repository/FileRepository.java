package com.example.demosys.domain.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demosys.domain.files.entity.FileEntity;

/**
 * FileRepository
 * JPA 仓储占位：如你只用 MyBatis/MinIO，可删除该类。
 */
public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
