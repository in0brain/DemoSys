package com.example.demosys.domain.admissions.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CandidatesInterviewScoreRequest {

    /** 复试成绩，0~100 */
    private BigDecimal score;
}
