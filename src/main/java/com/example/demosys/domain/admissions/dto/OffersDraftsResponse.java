package com.example.demosys.domain.admissions.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OffersDraftsResponse {

    /** 草稿批次号：一次生成一个，用于发布 */
    private String draftId;

    /** 草稿名单 */
    private List<DraftItem> items = new ArrayList<>();

    @Data
    public static class DraftItem {

        /** admissions_candidate.id */
        private Long candidateId;

        /** admissions_candidate.exam_no */
        private String examNo;

        /** admissions_candidate.name */
        private String name;

        /** admissions_candidate.major */
        private String major;

        /** admissions_offer_draft.admit_flag：0/1 */
        private Integer admitFlag;
    }
}

