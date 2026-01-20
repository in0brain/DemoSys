package com.example.demosys.domain.admissions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * admissions_offer_publication（正式公示录取名单表）
 *
 * 字段：
 *  - id           bigint PK
 *  - candidate_id bigint 关联 admissions_candidate.id
 *  - create_time  datetime
 *  - update_time  datetime
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferPublicationEntity {

    private Long id;

    /** 来自哪个草稿批次（= draftId） */
    private String draftId;

    /** 公示批次ID（可以直接等于 draftId） */
    private String publicationId;

    private Long candidateId;

    /** 公示状态：PUBLISHED */
    private String status;

    /** 公示时间（语义上比 createTime 清晰） */
    private LocalDateTime publishedAt;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
