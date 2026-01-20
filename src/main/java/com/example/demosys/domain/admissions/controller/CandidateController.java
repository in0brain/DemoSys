package com.example.demosys.domain.admissions.controller;

import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.admissions.dto.CandidatesInterviewScoreRequest;
import com.example.demosys.domain.admissions.dto.CandidatesInterviewScoreResponse;
import com.example.demosys.domain.admissions.dto.CandidatesResponse;
import com.example.demosys.domain.admissions.service.CandidateService;
import com.example.demosys.domain.admissions.service.InterviewScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admissions")
public class CandidateController {

    private final CandidateService candidateService;
    private final InterviewScoreService interviewScoreService;

    public CandidateController(CandidateService candidateService,
                               InterviewScoreService interviewScoreService) {
        this.candidateService = candidateService;
        this.interviewScoreService = interviewScoreService;
    }

    /**
     * 查询考生列表（主干：分页即可；filters 先不处理）
     */
    @GetMapping("/candidates")
    public ApiResponse listCandidates(@RequestParam(required = false) Integer page,
                                                          @RequestParam(required = false) Integer pageSize,
                                                          @RequestParam(required = false) Map<String, String> filters) {

        int p = (page == null ? 1 : page);
        int ps = (pageSize == null ? 10 : pageSize);

        CandidatesResponse resp = candidateService.page(p, ps);
        return ApiResponse.ok(resp);
    }

    /**
     * 考生详情（主干不依赖，先占位可点通）
     * 你后续要真正详情：我再给你补 CandidateService.getById + mapper SQL。
     */
    @GetMapping("/candidates/{candidateId}")
    public ApiResponse getCandidatesBy(@PathVariable("candidateId") String candidateId) {
        // TODO: 主干不需要详情，先占位
        return ApiResponse.ok(null);
    }

    /**
     * 录入/更新单个考生复试成绩（主干：一次性提交后锁定；不做更新）
     */
    @PutMapping("/candidates/{candidateId}/interview-score")
    public ApiResponse updateCandidatesByInterviewScore(
            @PathVariable("candidateId") String candidateId,
            @RequestBody CandidatesInterviewScoreRequest request) {

        Long cid = Long.valueOf(candidateId);
        interviewScoreService.submit(cid, request.getScore());

        CandidatesInterviewScoreResponse resp = new CandidatesInterviewScoreResponse();
        resp.setCandidateId(cid);
        resp.setLocked(true);
        return ApiResponse.ok(resp);
    }
}
