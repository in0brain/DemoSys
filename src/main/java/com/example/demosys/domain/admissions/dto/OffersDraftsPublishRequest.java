package com.example.demosys.domain.admissions.dto;

import lombok.Data;

@Data
public class OffersDraftsPublishRequest {
    // 可选：操作人（从登录态取也行）
    private Long operatorId;

    // 可选：发布备注（比如“2026春季拟录取公示”）
    private String remark;
}