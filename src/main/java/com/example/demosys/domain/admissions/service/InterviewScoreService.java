package com.example.demosys.domain.admissions.service;

import com.example.demosys.domain.admissions.dto.CandidatesInterviewScoreResponse;
import com.example.demosys.domain.admissions.dto.InterviewScoreListResponse;

import java.math.BigDecimal;

public interface InterviewScoreService {

    /**
     * 一次性提交复试成绩（提交后锁定，不可重复提交）
     */
    void submit(Long candidateId, BigDecimal score);
    CandidatesInterviewScoreResponse getByCandidateId(Long candidateId);
    InterviewScoreListResponse list(int page, int pageSize);
}
