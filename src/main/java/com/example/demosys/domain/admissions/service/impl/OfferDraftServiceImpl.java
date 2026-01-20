package com.example.demosys.domain.admissions.service.impl;

import com.example.demosys.domain.admissions.dto.OffersDraftAddItemResponse;
import com.example.demosys.domain.admissions.dto.OffersDraftItemsBatchAddRequest;
import com.example.demosys.domain.admissions.dto.OffersDraftItemsBatchAddResponse;
import com.example.demosys.domain.admissions.dto.OffersDraftsResponse;
import com.example.demosys.domain.admissions.mapper.OfferDraftMapper;
import com.example.demosys.domain.admissions.service.OfferDraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferDraftServiceImpl implements OfferDraftService {

    private final OfferDraftMapper offerDraftMapper;
    private volatile String currentDraftId;

    private static final DateTimeFormatter DRAFT_FMT =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    @Override
    @Transactional
    public OffersDraftsResponse createDraft() {
        // 1) 后端生成 draftId（B方案）
        String draftId = genDraftId();
        this.currentDraftId = draftId;
        // 2) 最简：清空旧草稿（只保留一份草稿）
        offerDraftMapper.clearAll();

        // 3) 从复试成绩生成草稿，并写入 draft_id
        // ⚠️ 你的 XML insert 必须包含 admit_flag 字段（建议默认 1）
        offerDraftMapper.insertFromInterviewScore(draftId);

        // 4) 按 draftId 查询草稿列表
        List<OfferDraftMapper.OfferDraftRow> rows = offerDraftMapper.selectItems(draftId);

        // 5) 组装响应
        OffersDraftsResponse resp = new OffersDraftsResponse();
        resp.setDraftId(draftId);

        List<OffersDraftsResponse.DraftItem> items = new ArrayList<>();
        for (OfferDraftMapper.OfferDraftRow r : rows) {
            OffersDraftsResponse.DraftItem it = new OffersDraftsResponse.DraftItem();
            it.setCandidateId(r.candidateId);
            it.setExamNo(r.examNo);
            it.setName(r.name);
            it.setMajor(r.major);              // ✅ 新增：草稿页展示专业
            it.setAdmitFlag(r.admitFlag != null ? r.admitFlag : 1); // ✅ 新增：拟录取标记
            items.add(it);
        }
        resp.setItems(items);
        return resp;
    }

    private String genDraftId() {
        return "DRAFT_" + LocalDateTime.now().format(DRAFT_FMT);
    }

    @Override
    @Transactional(readOnly = true)
    public OffersDraftsResponse getDraft(String draftId) {
        // 当前策略：草稿表里只有一份（因为 createDraft 会 clearAll）
        // 所以这里就按 draftId 查，查不到就返回空 items + draftId（给前端明确反馈）
        List<OfferDraftMapper.OfferDraftRow> rows = offerDraftMapper.selectItems(draftId);

        OffersDraftsResponse resp = new OffersDraftsResponse();
        resp.setDraftId(draftId);

        List<OffersDraftsResponse.DraftItem> items = new ArrayList<>();
        for (OfferDraftMapper.OfferDraftRow r : rows) {
            OffersDraftsResponse.DraftItem it = new OffersDraftsResponse.DraftItem();
            it.setCandidateId(r.candidateId);
            it.setExamNo(r.examNo);
            it.setName(r.name);
            it.setMajor(r.major);
            it.setAdmitFlag(r.admitFlag);
            items.add(it);
        }
        resp.setItems(items);
        return resp;
    }

    @Override
    @Transactional
    public OffersDraftAddItemResponse addCandidate(Long candidateId) {
        String draftId = this.currentDraftId;
        if (draftId == null || draftId.isBlank()) {
            // 还没生成草稿，要求先 POST /offers/drafts
            throw new IllegalStateException("当前没有草稿批次，请先生成草稿");
        }

        // 可选：主干校验：必须已录入成绩（用 score 表判定）
        // 如果你不想查，就先不做；但建议加，避免把未录入的人加入草稿。
        // 这里先按最简不查。

        int n = offerDraftMapper.insertOne(draftId, candidateId, 1);

        OffersDraftAddItemResponse resp = new OffersDraftAddItemResponse();
        resp.setDraftId(draftId);
        resp.setCandidateId(candidateId);
        resp.setAdded(n > 0);
        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferDraftMapper.OfferDraftRow> listAllItems() {
        // 传 null / "" 都行，你 XML 的 <if> 会不拼 where
        return offerDraftMapper.selectItems(null);
    }

    @Transactional
    @Override
    public OffersDraftItemsBatchAddResponse addDraftItemsBatch(OffersDraftItemsBatchAddRequest req) {
        List<Long> ids = (req == null || req.getCandidateIds() == null) ? List.of() : req.getCandidateIds();
        if (ids.isEmpty()) {
            OffersDraftItemsBatchAddResponse resp = new OffersDraftItemsBatchAddResponse();
            resp.setDraftId(req != null ? req.getDraftId() : null);
            resp.setInserted(0);
            return resp;
        }

        // draftId：前端不传就后端生成
        String draftId = (req.getDraftId() == null || req.getDraftId().trim().isEmpty())
                ? genDraftId()
                : req.getDraftId().trim();

        int inserted = offerDraftMapper.batchInsertCandidates(draftId, ids);

        OffersDraftItemsBatchAddResponse resp = new OffersDraftItemsBatchAddResponse();
        resp.setDraftId(draftId);
        resp.setInserted(inserted);
        return resp;
    }
}
