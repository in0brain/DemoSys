package com.example.demosys.domain.education.controller;

import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.education.dto.CreditsMeResponse;
import com.example.demosys.domain.education.dto.CreditsOverviewResponse;
import com.example.demosys.domain.education.service.CreditSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class CreditController {

    private final CreditSummaryService creditSummaryService;

    @GetMapping("/credits/me")
    public ApiResponse listCreditsMe(@RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Map<String, String> filters) {
        CreditsMeResponse resp = creditSummaryService.me();
        return ApiResponse.ok(resp);
    }

    /**
     * 学院：学分总览（按累计学分从低到高）
     * SR：不分页，后续再加 page/pageSize
     */
    @GetMapping("/credits/overview")
    public ApiResponse listCreditsOverview(@RequestParam(required = false) String keyword) {
        // keyword 可选：按学号/姓名模糊（SR 可先不实现也行）
        CreditsOverviewResponse resp = creditSummaryService.overview(keyword);
        return ApiResponse.ok(resp);
    }

    /**
     * 学院：指定学生学分进度
     */
    @GetMapping("/credits/students/{studentId}")
    public ApiResponse getCreditsStudentsBy(@PathVariable("studentId") Long studentId) {
        CreditsMeResponse resp = creditSummaryService.byStudentId(studentId);
        return ApiResponse.ok(resp);
    }

}
