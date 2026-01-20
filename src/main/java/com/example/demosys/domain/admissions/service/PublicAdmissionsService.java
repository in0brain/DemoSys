package com.example.demosys.domain.admissions.service;

import com.example.demosys.domain.admissions.dto.PublicAdmissionsOffersResponse;

public interface PublicAdmissionsService {
    PublicAdmissionsOffersResponse query(String keyword);
}
