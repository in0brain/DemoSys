package com.example.demosys.domain.auth.service;

import com.example.demosys.domain.auth.dto.LocalLoginRequest;
import com.example.demosys.domain.auth.dto.LocalLoginResponse;

public interface LocalAuthService {
    LocalLoginResponse login(LocalLoginRequest req);
}
