package com.example.demosys.domain.files.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * FileEntity
 * 文件元数据实体占位类：可映射到 files 表（fileKey、bucket、size、sha256、owner、bizType...）。
 */
@Data
@Entity
@Table(name = "fileentity")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileKey;
    private String filename;
    private Long sizeBytes;
    private String contentType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
