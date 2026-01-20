package com.example.demosys.domain.admissions.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OffersDraftItemsBatchAddRequest {

    /** 勾选的 candidateId 列表 */
    private List<Long> candidateIds = new ArrayList<>();

    /** 可选：前端如果传了 draftId 就用；不传则后端生成 */
    private String draftId;
}
