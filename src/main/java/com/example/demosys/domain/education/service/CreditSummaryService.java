package com.example.demosys.domain.education.service;

import com.example.demosys.domain.education.dto.CreditsMeResponse;
import com.example.demosys.domain.education.dto.CreditsOverviewResponse;

public interface CreditSummaryService {

    /**
     * 学生本人学分与已通过课程
     */
    CreditsMeResponse me();

    /**
     * 学院：学分总览（全体学生，按累计学分从低到高）
     * keyword 可选：学号/姓名模糊
     */
    CreditsOverviewResponse overview(String keyword);

    /**
     * 学院：指定学生学分与已通过课程
     */
    CreditsMeResponse byStudentId(Long studentId);
}
