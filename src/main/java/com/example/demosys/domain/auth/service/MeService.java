package com.example.demosys.domain.auth.service;

import com.example.demosys.domain.auth.dto.MeDisplayDTO;

/**
 * MeService
 * 自动生成：身份与权限模块资源 Me（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface MeService {

    /**
     * 描述：当前登录用户信息（含角色/院系/权限）
     * 关联：IR-AUTH-1
     * 备注：前端菜单/按钮控制
     */
    Object getProfile(java.util.Map<String, Object> params);
    /**
     * 描述：权限点列表
     * 关联：IR-AUTH-1, IR-AUTH-2
     * 备注：可选
     */

    MeDisplayDTO getMeDisplay(java.util.Map<String, Object> params);

    Object listPermissions(java.util.Map<String, Object> params);

}
