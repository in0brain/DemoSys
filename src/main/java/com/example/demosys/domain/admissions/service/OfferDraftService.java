package com.example.demosys.domain.admissions.service;

import com.example.demosys.domain.admissions.dto.OffersDraftAddItemResponse;
import com.example.demosys.domain.admissions.dto.OffersDraftItemsBatchAddRequest;
import com.example.demosys.domain.admissions.dto.OffersDraftItemsBatchAddResponse;
import com.example.demosys.domain.admissions.dto.OffersDraftsResponse;
import com.example.demosys.domain.admissions.mapper.OfferDraftMapper;

import java.util.List;

public interface OfferDraftService {
    OffersDraftsResponse createDraft();
    OffersDraftsResponse getDraft(String draftId);
    OffersDraftAddItemResponse addCandidate(Long candidateId);
    List<OfferDraftMapper.OfferDraftRow> listAllItems();
    OffersDraftItemsBatchAddResponse addDraftItemsBatch(OffersDraftItemsBatchAddRequest req);

}
