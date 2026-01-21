package com.example.demosys.domain.education.service.impl;

import com.example.demosys.common.exception.BizException;
import com.example.demosys.domain.education.dto.CreditsMeResponse;
import com.example.demosys.domain.education.dto.CreditsOverviewResponse;
import com.example.demosys.domain.education.entity.CreditSummary;
import com.example.demosys.domain.education.mapper.CreditMapper;
import com.example.demosys.domain.education.service.CreditSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreditSummaryServiceImpl implements CreditSummaryService {

    private final CreditMapper creditMapper;

    /**
     * 临时：模拟当前用户（后续接 JWT/SecurityContext 替换）
     */
    private Long getCurrentUserId() {
        return 1001L;
    }

    @Override
    public CreditsMeResponse me() {
        Long studentId = getCurrentUserId();
        return build(studentId);
    }

    @Override
    public CreditsMeResponse byStudentId(Long studentId) {
        if (studentId == null) {
            throw new BizException("studentId 不能为空");
        }
        // TODO（后续）：这里加鉴权，只允许学院管理员
        return build(studentId);
    }

    @Override
    public CreditsOverviewResponse overview(String keyword) {
        // TODO（后续）：这里加鉴权，只允许学院管理员
        String kw = (keyword == null) ? null : keyword.trim();
        CreditsOverviewResponse resp = new CreditsOverviewResponse();
        resp.setItems(creditMapper.selectCreditOverview(kw));
        return resp;
    }

    /**
     * 组装：学分汇总 + 已通过课程
     */
    private CreditsMeResponse build(Long studentId) {

        // 1) 汇总学分（改造后：summary 不再为 null，至少 totalCredit=0）
        CreditSummary summary = creditMapper.selectCreditSummaryByStudentId(studentId);
        BigDecimal total = (summary == null || summary.getTotalCredit() == null)
                ? BigDecimal.ZERO
                : summary.getTotalCredit();

        // 2) 已通过课程（学院终审通过）
        List<Map<String, Object>> rows = creditMapper.selectPassedCoursesByStudentId(studentId);
        List<CreditsMeResponse.PassedCourse> passed = new ArrayList<>();

        if (rows != null) {
            for (Map<String, Object> r : rows) {
                CreditsMeResponse.PassedCourse pc = new CreditsMeResponse.PassedCourse();

                pc.setCourseCode(asString(r.get("courseCode")));
                pc.setCourseName(asString(r.get("courseName")));
                pc.setCredit(asBigDecimal(r.get("credit")));
                pc.setFromEnrollmentId(asLong(r.get("fromEnrollmentId")));
                pc.setApprovedTime(asDateTimeText(r.get("approvedTime")));

                passed.add(pc);
            }
        }

        CreditsMeResponse resp = new CreditsMeResponse();
        resp.setStudentId(studentId);
        resp.setTotalCredit(total);
        resp.setPassedCourses(passed);
        return resp;
    }

    // =========================
    // Map -> 类型安全转换（避免 JDBC 类型差异导致 ClassCastException）
    // =========================
    private String asString(Object v) {
        return v == null ? null : String.valueOf(v);
    }

    private Long asLong(Object v) {
        if (v == null) return null;
        if (v instanceof Long) return (Long) v;
        if (v instanceof Integer) return ((Integer) v).longValue();
        if (v instanceof Number) return ((Number) v).longValue();
        try {
            return Long.parseLong(String.valueOf(v));
        } catch (Exception ignored) {
            return null;
        }
    }

    private BigDecimal asBigDecimal(Object v) {
        if (v == null) return null;
        if (v instanceof BigDecimal) return (BigDecimal) v;
        if (v instanceof Number) return BigDecimal.valueOf(((Number) v).doubleValue());
        try {
            return new BigDecimal(String.valueOf(v));
        } catch (Exception ignored) {
            return null;
        }
    }

    private String asDateTimeText(Object v) {
        if (v == null) return null;
        if (v instanceof LocalDateTime) return v.toString();
        try {
            if (v instanceof java.sql.Timestamp) {
                return ((java.sql.Timestamp) v).toLocalDateTime().toString();
            }
        } catch (Exception ignored) { }
        return String.valueOf(v);
    }
}
