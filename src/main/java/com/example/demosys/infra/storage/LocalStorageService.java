package com.example.demosys.infra.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * LocalStorageService（占位实现）
 * 生产建议：fileKey -> 路径映射写入 DB（files 模块元数据表），避免目录遍历风险。
 */
public class LocalStorageService implements StorageService {

    private final StorageProperties props;

    public LocalStorageService(StorageProperties props) {
        this.props = props;
    }

    @Override
    public String upload(MultipartFile file, String bizType, String ownerId) {
        String ext = "";
        String original = file.getOriginalFilename();
        if (original != null && original.contains(".")) {
            ext = original.substring(original.lastIndexOf('.'));
        }
        String key = bizType + "/" + ownerId + "/" + UUID.randomUUID().toString().replace("-", "") + ext;
        Path base = Paths.get(props.getLocal().getBaseDir());
        Path target = base.resolve(key);
        try {
            Files.createDirectories(target.getParent());
            file.transferTo(target.toFile());
            return key;
        } catch (IOException e) {
            throw new RuntimeException("Local upload failed: " + e.getMessage(), e);
        }
    }

    @Override
    public InputStream openStream(String fileKey) {
        try {
            Path base = Paths.get(props.getLocal().getBaseDir());
            File f = base.resolve(fileKey).toFile();
            return new FileInputStream(f);
        } catch (IOException e) {
            throw new RuntimeException("Local openStream failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String fileKey) {
        try {
            Path base = Paths.get(props.getLocal().getBaseDir());
            Files.deleteIfExists(base.resolve(fileKey));
        } catch (IOException e) {
            throw new RuntimeException("Local delete failed: " + e.getMessage(), e);
        }
    }
}
