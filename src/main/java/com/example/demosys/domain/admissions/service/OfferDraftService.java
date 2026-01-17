package com.example.demosys.domain.admissions.service;

import com.example.demosys.domain.admissions.dto.OffersDraftsPublishRequest;
import com.example.demosys.domain.admissions.dto.OffersDraftsRequest;
import com.example.demosys.domain.admissions.dto.OffersDraftsSubmitApprovalRequest;

/**
 * OfferDraftService
 * 自动生成：招生模块资源 OfferDraft（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface OfferDraftService {

    /**
     * 分组：2.2 拟录取公示（SR-ADM-2）
     * 描述：拟录取草稿列表
     * 角色：招生管理员
     * 关联：SR-ADM-2
     */
    Object listOffersDrafts(java.util.Map<String, Object> params);
    /**
     * 分组：2.2 拟录取公示（SR-ADM-2）
     * 描述：生成拟录取草稿（按规则/批次）
     * 角色：招生管理员
     * 关联：SR-ADM-2
     */
    Object createOffersDrafts(OffersDraftsRequest request);
    /**
     * 分组：2.2 拟录取公示（SR-ADM-2）
     * 描述：草稿详情预览（脱敏视图）
     * 角色：招生管理员
     * 关联：SR-ADM-2
     */
    Object getOffersDraftsByid(java.util.Map<String, Object> params);
    /**
     * 分组：2.2 拟录取公示（SR-ADM-2）
     * 描述：发布公示（审核通过后）
     * 角色：招生管理员
     * 关联：SR-ADM-2
     */
    Object createOffersDraftsByidPublish(OffersDraftsPublishRequest request);
    /**
     * 分组：2.2 拟录取公示（SR-ADM-2）
     * 描述：提交发布审批
     * 角色：招生管理员
     * 关联：SR-ADM-2
     */
    Object createOffersDraftsByidSubmitApproval(OffersDraftsSubmitApprovalRequest request);

}
