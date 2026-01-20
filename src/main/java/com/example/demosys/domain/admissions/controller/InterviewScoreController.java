package com.example.demosys.domain.admissions.controller;

import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.admissions.service.InterviewScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admissions")
@RequiredArgsConstructor
public class InterviewScoreController {

    private final InterviewScoreService interviewScoreService;

    /**
     * ✅ 复试成绩列表（联表展示用）
     * 用于：拟录取草稿页先展示复试成绩，再勾选加入拟录取
     */
    @GetMapping("/interview-scores")
    public ApiResponse listInterviewScores(@RequestParam(required = false) Integer page,
                                           @RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) Map<String, String> filters) {

        int p = (page == null || page <= 0) ? 1 : page;
        int ps = (pageSize == null || pageSize <= 0) ? 10 : pageSize;

        // 主干：先不做 keyword/filters 筛选（你后续需要再扩展）
        return ApiResponse.ok(interviewScoreService.list(p, ps));
    }

    @GetMapping("/interview-scores/export")
    public ApiResponse listInterviewScoresExport(@RequestParam(required = false) Integer page,
                                                 @RequestParam(required = false) Integer pageSize,
                                                 @RequestParam(required = false) Map<String, String> filters) {
        // 主干不做导出
        return ApiResponse.ok(null);
    }

    @PostMapping("/interview-scores/import")
    public ApiResponse createInterviewScoresImport(@RequestBody Object request) {
        // 主干不做导入
        return ApiResponse.ok(null);
    }

    @PostMapping("/interview-scores/lock")
    public ApiResponse createInterviewScoresLock(@RequestBody Object request) {
        // 主干不做全局锁定（一次性提交：有记录即视为锁定）
        return ApiResponse.ok(null);
    }
}
