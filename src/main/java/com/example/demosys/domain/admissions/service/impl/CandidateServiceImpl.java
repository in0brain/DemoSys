package com.example.demosys.domain.admissions.service.impl;

import com.example.demosys.domain.admissions.dto.CandidatesResponse;
import com.example.demosys.domain.admissions.mapper.CandidateMapper;
import com.example.demosys.domain.admissions.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateMapper candidateMapper;

    @Override
    public CandidatesResponse page(int page, int pageSize) {
        int p = Math.max(page, 1);
        int ps = Math.min(Math.max(pageSize, 1), 100);
        int offset = (p - 1) * ps;

        List<CandidateMapper.CandidateRow> rows = candidateMapper.selectPage(offset, ps);
        long total = candidateMapper.countAll();

        CandidatesResponse resp = new CandidatesResponse();
        resp.setPage(p);
        resp.setPageSize(ps);
        resp.setTotal(total);

        List<CandidatesResponse.CandidateItem> items = new ArrayList<>();
        for (CandidateMapper.CandidateRow r : rows) {
            CandidatesResponse.CandidateItem it = new CandidatesResponse.CandidateItem();
            it.setId(r.id);
            it.setExamNo(r.examNo);
            it.setName(r.name);
            it.setMajor(r.major); // ✅ 新增：报考专业

            // interview_locked: 0/1 -> Boolean
            it.setInterviewLocked(r.interviewLocked != null && r.interviewLocked == 1);

            items.add(it);
        }

        resp.setItems(items);
        return resp;
    }
}
