package com.example.demosys.domain.admissions.dto;

import lombok.Data;

@Data
public class OffersDraftAddItemResponse {
    private String draftId;
    private Long candidateId;
    private Boolean added;
}
