package com.example.demosys.domain.files.service;

import com.example.demosys.domain.files.dto.RootRequest;

/**
 * FileService
 * 自动生成：Files 模块资源 File（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface FileService {

    /**
     * 分组：文件服务
     * 描述：上传文件（返回 fileId）
     * 角色：登录用户
     * 关联：IR-FILE-1
     */
    Object createRoot(RootRequest request);
    /**
     * 分组：文件服务
     * 描述：下载文件（鉴权、审计）
     * 角色：登录用户
     * 关联：IR-FILE-1
     */
    Object getByid(java.util.Map<String, Object> params);

}
