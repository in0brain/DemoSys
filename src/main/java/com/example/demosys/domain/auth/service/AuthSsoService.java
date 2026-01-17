package com.example.demosys.domain.auth.service;

import com.example.demosys.domain.auth.dto.SsoCallbackRequest;

/**
 * AuthSsoService
 * 自动生成：身份与权限模块资源 AuthSso（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface AuthSsoService {

    /**
     * 描述：SSO 回调换取 token
     * 关联：IR-AUTH-1
     * 备注：网关也可做
     */
    Object createSsoCallback(SsoCallbackRequest request);
    /**
     * 描述：获取统一认证跳转地址
     * 关联：IR-AUTH-1
     * 备注：web 前端用
     */
    Object listSsoLoginUrl(java.util.Map<String, Object> params);

}
