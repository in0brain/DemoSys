package com.example.demosys.domain.admissions.service.impl;

import com.example.demosys.domain.admissions.mapper.OfferPublicationMapper;
import com.example.demosys.domain.admissions.service.OfferPublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OfferPublicationServiceImpl implements OfferPublicationService {

    private final OfferPublicationMapper offerPublicationMapper;

    @Transactional
    @Override
    public void publish(String draftId) {
        // draftId 仅作为接口参数（主干不做版本/历史）
        offerPublicationMapper.insertFromDraft(draftId);
    }
}
