package com.example.demosys.domain.admissions.service;

import com.example.demosys.domain.admissions.dto.OffersPublicationsCloseRequest;
import com.example.demosys.domain.admissions.dto.OffersPublicationsRequest;

/**
 * OfferPublicationService
 * 自动生成：招生模块资源 OfferPublication（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface OfferPublicationService {

    /**
     * 分组：2.2 拟录取公示（SR-ADM-2）
     * 描述：公示期变更（留痕/版本）
     * 角色：招生管理员
     * 关联：SR-ADM-2
     */
    Object updateOffersPublicationsByid(OffersPublicationsRequest request);
    /**
     * 分组：2.2 拟录取公示（SR-ADM-2）
     * 描述：公示结束/下线归档
     * 角色：招生管理员
     * 关联：SR-ADM-2
     */
    Object createOffersPublicationsByidClose(OffersPublicationsCloseRequest request);

}
