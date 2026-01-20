package com.example.demosys.domain.admissions.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OfferDraftEntity {

    private Long id;            // 主键（单行记录ID）
    private Long draftId;       // 草稿批次ID（关键：publish 用它分组）
    private Long candidateId;   // 考生ID

    // 是否拟录取（你发布时按这个过滤 true）
    private Boolean admitFlag;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
