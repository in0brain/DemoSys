package com.example.demosys.domain.admissions.service;

public interface OfferPublicationService {

    /**
     * 发布公示（最简：把草稿写入 publication）
     * draftId 仅做接口参数（先不做版本/历史）
     */
    void publish(String draftId);
}
