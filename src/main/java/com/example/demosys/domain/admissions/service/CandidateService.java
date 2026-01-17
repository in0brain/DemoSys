package com.example.demosys.domain.admissions.service;

/**
 * CandidateService
 * 自动生成：招生模块资源 Candidate（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface CandidateService {

    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：查询考生列表（筛选：专业/批次/姓名/考号）
     * 角色：招生管理员
     * 关联：SR-ADM-1
     */
    Object listCandidates(java.util.Map<String, Object> params);
    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：考生详情
     * 角色：招生管理员
     * 关联：SR-ADM-1
     */
    Object getCandidatesByid(java.util.Map<String, Object> params);

}
