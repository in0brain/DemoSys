package com.example.demosys.domain.admissions.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.admissions.dto.PublicAdmissionsOffersResponse;
import com.example.demosys.domain.admissions.service.PublicAdmissionsService;

import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicAdmissionsOfferController {

    private final PublicAdmissionsService publicAdmissionsService;

    public PublicAdmissionsOfferController(PublicAdmissionsService publicAdmissionsService) {
        this.publicAdmissionsService = publicAdmissionsService;
    }

    @GetMapping("/admissions/offers")
    public ApiResponse listAdmissionsOffers(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer pageSize,
                                            @RequestParam(required = false) String keyword,
                                            @RequestParam(required = false) Map<String, String> filters) {

        String kw = keyword;
        if (filters != null) {
            kw = filters.get("keyword");
            if (kw == null) {
                // 兼容：有人可能传 name/examNo
                kw = filters.getOrDefault("name", filters.get("examNo"));
            }
        }

        String safeKw = (kw == null) ? "" : kw.trim();
        return ApiResponse.ok(publicAdmissionsService.query(safeKw));
    }
}
