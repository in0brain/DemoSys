package com.example.demosys.domain.admissions.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CandidatesInterviewScoreResponse {

    /** admissions_interview_score.id */
    private Long id;

    /** admissions_interview_score.candidate_id */
    private Long candidateId;

    /** admissions_interview_score.score */
    private BigDecimal score;

    /** 主干：提交后即锁定（不可修改） */
    private Boolean locked;

    /** admissions_interview_score.create_time */
    private LocalDateTime createdAt;

    /** admissions_interview_score.update_time（一般=createdAt） */
    private LocalDateTime updatedAt;
}
