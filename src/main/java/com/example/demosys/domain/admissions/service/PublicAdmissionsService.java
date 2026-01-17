package com.example.demosys.domain.admissions.service;

/**
 * PublicAdmissionsService
 * 自动生成：招生模块资源 PublicAdmissions（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface PublicAdmissionsService {

    /**
     * 分组：公众查询（无需登录或弱鉴权）
     * 描述：公示查询（按考号/姓名）
     * 角色：公众/弱鉴权
     * 关联：IR-ADM-3
     */
    Object listPublicAdmissionsOffers(java.util.Map<String, Object> params);

}
