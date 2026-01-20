package com.example.demosys.domain.admissions.dto;

import lombok.Data;

@Data
public class OffersDraftItemsBatchAddResponse {
    private String draftId;
    private Integer inserted; // 本次插入条数
}
