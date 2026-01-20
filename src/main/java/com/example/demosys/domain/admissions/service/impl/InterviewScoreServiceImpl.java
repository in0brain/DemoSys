package com.example.demosys.domain.admissions.service.impl;

import com.example.demosys.domain.admissions.dto.CandidatesInterviewScoreResponse;
import com.example.demosys.domain.admissions.dto.InterviewScoreListResponse;
import com.example.demosys.domain.admissions.mapper.CandidateMapper;
import com.example.demosys.domain.admissions.mapper.InterviewScoreMapper;
import com.example.demosys.domain.admissions.service.InterviewScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewScoreServiceImpl implements InterviewScoreService {

    private final CandidateMapper candidateMapper;
    private final InterviewScoreMapper interviewScoreMapper;

    @Transactional
    @Override
    public void submit(Long candidateId, BigDecimal score) {
        if (candidateId == null) {
            throw new IllegalArgumentException("candidateId不能为空");
        }
        if (score == null) {
            throw new IllegalArgumentException("score不能为空");
        }
        if (score.compareTo(BigDecimal.ZERO) < 0 || score.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("分数范围应为0~100");
        }

        // 考生存在性（mapper 的 selectById 返回 CandidateRow）
        CandidateMapper.CandidateRow c = candidateMapper.selectById(candidateId);
        if (c == null) {
            throw new IllegalArgumentException("考生不存在");
        }

        // 一次性提交（已存在则拒绝）
        if (interviewScoreMapper.selectByCandidateId(candidateId) != null) {
            throw new IllegalStateException("成绩已提交并锁定，不可重复提交");
        }

        interviewScoreMapper.insert(candidateId, score);
    }

    @Override
    public CandidatesInterviewScoreResponse getByCandidateId(Long candidateId) {
        var row = interviewScoreMapper.selectByCandidateId(candidateId);
        if (row == null) return null;

        CandidatesInterviewScoreResponse resp = new CandidatesInterviewScoreResponse();
        resp.setId(row.id);
        resp.setCandidateId(row.candidateId);
        resp.setScore(row.score);
        resp.setLocked(true);
        // createdAt/updatedAt 需要 mapper select 把 create_time/update_time 查出来（见下）
        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    public InterviewScoreListResponse list(int page, int pageSize) {

        // 1) 参数兜底
        int p = page <= 0 ? 1 : page;
        int ps = pageSize <= 0 ? 10 : pageSize;
        int offset = (p - 1) * ps;

        // 2) 查询数据库（联表）
        List<InterviewScoreMapper.InterviewScoreListRow> rows =
                interviewScoreMapper.selectPage(offset, ps);

        long total = interviewScoreMapper.countAll();

        // 3) 组装返回 DTO
        InterviewScoreListResponse resp = new InterviewScoreListResponse();
        resp.setPage(p);
        resp.setPageSize(ps);
        resp.setTotal(total);

        List<InterviewScoreListResponse.Item> items = new ArrayList<>();
        for (InterviewScoreMapper.InterviewScoreListRow r : rows) {
            InterviewScoreListResponse.Item it = new InterviewScoreListResponse.Item();
            it.setCandidateId(r.candidateId);
            it.setExamNo(r.examNo);
            it.setName(r.name);
            it.setMajor(r.major);
            it.setScore(r.score);
            items.add(it);
        }
        resp.setItems(items);

        return resp;
    }

}
