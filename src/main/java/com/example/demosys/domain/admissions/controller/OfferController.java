package com.example.demosys.domain.admissions.controller;

import com.example.demosys.domain.admissions.dto.OffersDraftItemsBatchAddRequest;
import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.admissions.dto.OffersDraftsPublishResponse;
import com.example.demosys.domain.admissions.service.OfferDraftService;
import com.example.demosys.domain.admissions.service.OfferPublicationService;

import java.util.Map;

@RestController
@RequestMapping("/admissions")
public class OfferController {

    private final OfferDraftService offerDraftService;
    private final OfferPublicationService offerPublicationService;

    public OfferController(OfferDraftService offerDraftService,
                           OfferPublicationService offerPublicationService) {
        this.offerDraftService = offerDraftService;
        this.offerPublicationService = offerPublicationService;
    }

    /**
     * ⚠️ 不建议再用 GET 生成草稿（保留接口但不做事，避免误点）
     */
    @GetMapping("/offers/drafts")
    public ApiResponse listOffersDrafts() {
        return ApiResponse.ok("Use POST /admissions/offers/drafts to create a draft.");
    }
    /**
     * ✅ 正式生成草稿：POST
     * Step1：生成 draft
     */
    @PostMapping("/offers/drafts")
    public ApiResponse createOffersDrafts(@RequestBody(required = false) Object request) {
        return ApiResponse.ok(offerDraftService.createDraft());
    }

    /**
     * ⚠️ 当前版本不支持按 draftId 查询历史草稿
     * 所以不要在这里 createDraft（否则会制造新 draft）
     */
    @GetMapping("/offers/drafts/{draftId}")
    public ApiResponse getOffersDraftsBy(@PathVariable("draftId") String draftId) {
        return ApiResponse.ok(offerDraftService.getDraft(draftId));
    }

    /**
     * Step2：发布公示
     */
    @PostMapping("/offers/drafts/{draftId}/publish")
    public ApiResponse createOffersDraftsByPublish(@PathVariable("draftId") String draftId,
                                                   @RequestBody(required = false) Object request) {
        offerPublicationService.publish(draftId);

        OffersDraftsPublishResponse resp = new OffersDraftsPublishResponse();
        resp.setDraftId(draftId);
        resp.setPublished(true);
        return ApiResponse.ok(resp);
    }

    /* ===== 以下接口：当前主干明确不做，保留占位即可 ===== */

    @PostMapping("/offers/drafts/{draftId}/submit-approval")
    public ApiResponse submitApproval(@PathVariable String draftId,
                                      @RequestBody Object request) {
        return ApiResponse.ok(null);
    }

    @PutMapping("/offers/publications/{pubId}")
    public ApiResponse updatePublication(@PathVariable String pubId,
                                         @RequestBody Object request) {
        return ApiResponse.ok(null);
    }

    @PostMapping("/offers/publications/{pubId}/close")
    public ApiResponse closePublication(@PathVariable String pubId,
                                        @RequestBody Object request) {
        return ApiResponse.ok(null);
    }

    @PostMapping("/offers/drafts/items")
    public ApiResponse addOffersDraftItem(@RequestBody Map<String, Object> body) {
        Object v = body == null ? null : body.get("candidateId");
        if (v == null) return ApiResponse.fail("candidateId is required");
        Long candidateId = Long.valueOf(String.valueOf(v));

        return ApiResponse.ok(offerDraftService.addCandidate(candidateId));
    }

    @GetMapping("/offers/drafts/items")
    public ApiResponse listOffersDraftItems() {
        // 全表查（不带 draftId 条件）
        return ApiResponse.ok(offerDraftService.listAllItems());
    }

    @PostMapping("/offers/drafts/items/batch")
    public ApiResponse addDraftItemsBatch(@RequestBody OffersDraftItemsBatchAddRequest request) {
        return ApiResponse.ok(offerDraftService.addDraftItemsBatch(request));
    }


}
