package com.example.demosys.domain.admissions.service;

import com.example.demosys.domain.admissions.dto.CandidatesInterviewScoreRequest;
import com.example.demosys.domain.admissions.dto.InterviewScoresImportRequest;
import com.example.demosys.domain.admissions.dto.InterviewScoresLockRequest;

/**
 * InterviewScoreService
 * 自动生成：招生模块资源 InterviewScore（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface InterviewScoreService {

    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：录入/更新单个考生复试成绩（含校验）
     * 角色：招生管理员
     * 关联：SR-ADM-1
     */
    Object updateCandidatesByidInterviewScore(CandidatesInterviewScoreRequest request);
    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：导出成绩汇总（Excel/PDF）
     * 角色：招生管理员
     * 关联：SR-ADM-1
     */
    Object listInterviewScoresExport(java.util.Map<String, Object> params);
    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：批量导入成绩（Excel）
     * 角色：招生管理员
     * 关联：SR-ADM-1
     * 备注：建议：返回 jobId，GET /jobs/{jobId} 查询导入结果
     */
    Object createInterviewScoresImport(InterviewScoresImportRequest request);
    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：成绩锁定（截止后只读）
     * 角色：招生管理员/更高权限
     * 关联：SR-ADM-1
     */
    Object createInterviewScoresLock(InterviewScoresLockRequest request);

}
