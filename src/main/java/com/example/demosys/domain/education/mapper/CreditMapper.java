package com.example.demosys.domain.education.mapper;
import com.example.demosys.domain.education.dto.CreditsOverviewResponse;
import com.example.demosys.domain.education.entity.CreditSummary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface CreditMapper {

    CreditSummary selectCreditSummaryByStudentId(@Param("studentId") Long studentId);

    int upsertAddCredit(@Param("studentId") Long studentId,
                        @Param("deltaCredit") BigDecimal deltaCredit);

    int overwriteTotalCredit(@Param("studentId") Long studentId,
                             @Param("totalCredit") BigDecimal totalCredit);

    List<Map<String, Object>> selectPassedCoursesByStudentId(@Param("studentId") Long studentId);

    // ✅ 新增：学院总览（低学分优先）
    List<CreditsOverviewResponse.Item> selectCreditOverview(@Param("keyword") String keyword);

}
