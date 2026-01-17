package com.example.demosys.infra.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * MinioStorageService（占位实现）
 * TODO：
 * - 引入 MinIO SDK 依赖后实现 upload/openStream/delete
 * - 将 bucket、endpoint、AK/SK 从 StorageProperties 读取
 */
public class MinioStorageService implements StorageService {

    private final StorageProperties props;

    public MinioStorageService(StorageProperties props) {
        this.props = props;
    }

    @Override
    public String upload(MultipartFile file, String bizType, String ownerId) {
        // TODO: implement via MinIO SDK
        throw new UnsupportedOperationException("MinIO not implemented yet");
    }

    @Override
    public InputStream openStream(String fileKey) {
        // TODO: implement via MinIO SDK
        throw new UnsupportedOperationException("MinIO not implemented yet");
    }

    @Override
    public void delete(String fileKey) {
        // TODO: implement via MinIO SDK
        throw new UnsupportedOperationException("MinIO not implemented yet");
    }
}
