package com.example.demosys.domain.admissions.service;

import com.example.demosys.domain.admissions.dto.CandidatesResponse;

public interface CandidateService {

    CandidatesResponse page(int page, int pageSize);

}
