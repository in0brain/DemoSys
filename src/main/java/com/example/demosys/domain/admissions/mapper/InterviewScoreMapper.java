package com.example.demosys.domain.admissions.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface InterviewScoreMapper {

    InterviewScoreRow selectByCandidateId(@Param("candidateId") Long candidateId);

    List<InterviewScoreListRow> selectPage(@Param("offset") int offset,
                                           @Param("limit") int limit);

    long countAll();

    class InterviewScoreListRow {
        public Long candidateId;
        public String examNo;
        public String name;
        public String major;
        public BigDecimal score;
    }

    int insert(@Param("candidateId") Long candidateId,
               @Param("score") BigDecimal score);

    class InterviewScoreRow {
        public Long id;
        public Long candidateId;
        public BigDecimal score;
    }
}
