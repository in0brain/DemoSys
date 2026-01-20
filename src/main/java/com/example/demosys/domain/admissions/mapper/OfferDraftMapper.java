package com.example.demosys.domain.admissions.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfferDraftMapper {

    /**
     * 生成新草稿前清空旧草稿（最简闭环）
     */
    int clearAll();

    /**
     * 从已录入成绩的考生集合生成草稿（最简：全量加入）
     */
    int insertFromInterviewScore(@Param("draftId") String draftId);

    /**
     * 查询草稿列表（按 draftId 过滤；如果 draftId 为空则查全部）
     */
    List<OfferDraftRow> selectItems(@Param("draftId") String draftId);


    int insertOne(@Param("draftId") String draftId,
                  @Param("candidateId") Long candidateId,
                  @Param("admitFlag") Integer admitFlag);

    class OfferDraftRow {
        public String draftId;
        public Long candidateId;
        public String examNo;
        public String name;
        public String major;
        public Integer admitFlag; // 0/1
    }

    int batchInsertCandidates(@Param("draftId") String draftId,
                              @Param("candidateIds") List<Long> candidateIds);
}
