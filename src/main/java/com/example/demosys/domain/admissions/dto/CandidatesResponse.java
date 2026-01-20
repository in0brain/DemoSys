package com.example.demosys.domain.admissions.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CandidatesResponse {

    /** 当前页 */
    private Integer page;

    /** 每页大小 */
    private Integer pageSize;

    /** 总条数 */
    private Long total;

    /** 列表数据 */
    private List<CandidateItem> items = new ArrayList<>();

    @Data
    public static class CandidateItem {

        /** admissions_candidate.id */
        private Long id;

        /** admissions_candidate.exam_no */
        private String examNo;

        /** admissions_candidate.name */
        private String name;

        /** admissions_candidate.major */
        private String major;

        /**
         * 是否已提交复试成绩并锁定
         * 来源：admissions_candidate.interview_locked（0/1）
         */
        private Boolean interviewLocked;
    }
}
