package com.example.demosys.domain.admissions.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CandidateMapper {

    /**
     * 考生分页列表（包含 interview_locked 锁定标志 + major）
     */
    List<CandidateRow> selectPage(@Param("offset") int offset,
                                  @Param("limit") int limit);

    long countAll();

    CandidateRow selectById(@Param("id") Long id);

    class CandidateRow {
        /** admissions_candidate.id */
        public Long id;

        /** admissions_candidate.exam_no */
        public String examNo;

        /** admissions_candidate.name */
        public String name;

        /** admissions_candidate.major */
        public String major;

        /** admissions_candidate.interview_locked：0/1 */
        public Integer interviewLocked;
    }
}
