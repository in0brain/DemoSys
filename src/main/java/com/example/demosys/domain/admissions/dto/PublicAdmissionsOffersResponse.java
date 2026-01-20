package com.example.demosys.domain.admissions.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PublicAdmissionsOffersResponse {

    /** 查询关键字（可选回显） */
    private String keyword;

    /** 查询结果 */
    private List<PublicOfferItem> items = new ArrayList<>();

    @Data
    public static class PublicOfferItem {
        /** admissions_candidate.name */
        private String name;

        /** admissions_candidate.exam_no */
        private String examNo;

        /** 录取状态：主干最简固定“已公示” */
        private String offerStatus;
    }
}
