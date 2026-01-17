package com.example.demosys.domain.notification.service;

import com.example.demosys.domain.notification.dto.ReadRequest;

/**
 * NotificationService
 * 自动生成：Notification 模块资源 Notification（来源：接口清单_RESTful_v1_对齐IR.xlsx）。
 */
public interface NotificationService {

    /**
     * 分组：通知中心
     * 描述：我的通知列表
     * 角色：登录用户
     * 关联：IR-NOTI-1
     */
    Object listRoot(java.util.Map<String, Object> params);
    /**
     * 分组：通知中心
     * 描述：标记已读
     * 角色：登录用户
     * 关联：IR-NOTI-1
     */
    Object createByidRead(ReadRequest request);

}
