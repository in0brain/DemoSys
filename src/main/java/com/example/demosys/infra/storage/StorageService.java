package com.example.demosys.infra.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * StorageService
 * 文件存储抽象：上层（files 模块）只依赖该接口，具体实现由 infra 提供。
 */
public interface StorageService {

    /** 上传文件并返回 fileKey（可用于后续下载/预览） */
    String upload(MultipartFile file, String bizType, String ownerId);

    /** 通过 fileKey 打开输入流（用于下载/预览） */
    InputStream openStream(String fileKey);

    /** 删除文件（可选） */
    void delete(String fileKey);
}
