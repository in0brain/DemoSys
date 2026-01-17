package com.example.demosys.domain.auth.service.impl;

import com.example.demosys.domain.auth.dto.SsoCallbackRequest;
import org.springframework.stereotype.Service;
import com.example.demosys.domain.auth.service.AuthSsoService;

/**
 * AuthSsoServiceImpl
 * 自动生成的实现骨架（TODO）。
 */
@Service
public class AuthSsoServiceImpl implements AuthSsoService {

    /**
     * 描述：SSO 回调换取 token
     * 关联：IR-AUTH-1
     * 备注：网关也可做
     */
    @Override
    public Object createSsoCallback(SsoCallbackRequest request) {
        // TODO: implement
        return null;
    }
    /**
     * 描述：获取统一认证跳转地址
     * 关联：IR-AUTH-1
     * 备注：web 前端用
     */
    @Override
    public Object listSsoLoginUrl(java.util.Map<String, Object> params) {
        // TODO: implement
        return null;
    }

}
