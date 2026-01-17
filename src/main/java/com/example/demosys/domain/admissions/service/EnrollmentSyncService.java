package com.example.demosys.domain.admissions.service;

import com.example.demosys.domain.admissions.dto.EnrollmentsSyncRequest;

/**
 * EnrollmentSyncService
 * 自动生成：招生模块资源 EnrollmentSync（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface EnrollmentSyncService {

    /**
     * 分组：2.3 录取新生同步（SR-ADM-3 / IR-ADM-1 / IR-ADM-2）
     * 描述：将已录取新生同步到培养学生库（批处理）
     * 角色：招生管理员
     * 关联：SR-ADM-3, IR-ADM-1, IR-ADM-2
     * 备注：内部接口（模块间）；可实现为 REST 或 MQ
     */
    Object createEnrollmentsSync(EnrollmentsSyncRequest request);
    /**
     * 分组：2.3 录取新生同步（SR-ADM-3 / IR-ADM-1 / IR-ADM-2）
     * 描述：同步对账状态/差异清单
     * 角色：招生管理员
     * 关联：SR-ADM-3, IR-ADM-1, IR-ADM-2
     */
    Object listEnrollmentsSyncStatus(java.util.Map<String, Object> params);

}
