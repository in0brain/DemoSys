package com.example.demosys.domain.admissions.service.impl;

import com.example.demosys.domain.admissions.dto.PublicAdmissionsOffersResponse;
import com.example.demosys.domain.admissions.mapper.PublicAdmissionsOfferMapper;
import com.example.demosys.domain.admissions.service.PublicAdmissionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicAdmissionsServiceImpl implements PublicAdmissionsService {

    private final PublicAdmissionsOfferMapper publicAdmissionsOfferMapper;

    @Override
    public PublicAdmissionsOffersResponse query(String keyword) {
        String kw = (keyword == null) ? "" : keyword.trim();

        // 不抛异常：保持与 Controller 的“空关键字返回空结果”策略一致
        PublicAdmissionsOffersResponse resp = new PublicAdmissionsOffersResponse();
        resp.setKeyword(kw);

        if (kw.isEmpty()) {
            resp.setItems(new ArrayList<>());
            return resp;
        }

        List<PublicAdmissionsOfferMapper.PublicOfferRow> rows =
                publicAdmissionsOfferMapper.query(kw);

        List<PublicAdmissionsOffersResponse.PublicOfferItem> items = new ArrayList<>();
        if (rows != null) {
            for (PublicAdmissionsOfferMapper.PublicOfferRow r : rows) {
                PublicAdmissionsOffersResponse.PublicOfferItem it = new PublicAdmissionsOffersResponse.PublicOfferItem();
                it.setName(r.name);
                it.setExamNo(r.examNo);

                // 兜底：避免 mapper 未返回 offerStatus 导致前端展示 null
                String status = r.offerStatus;
                if (status == null || status.trim().isEmpty()) {
                    status = "未公示";
                }
                it.setOfferStatus(status);

                items.add(it);
            }
        }

        resp.setItems(items);
        return resp;
    }
}
