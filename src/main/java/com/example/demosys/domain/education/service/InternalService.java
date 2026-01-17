package com.example.demosys.domain.education.service;

import com.example.demosys.domain.education.dto.InternalEducationRequest;

/**
 * InternalService
 * 自动生成：培养模块 Service（资源：internal）。
 */
public interface InternalService {

    /**
     * 分组：3.3 学分统计与毕业资格（SR-EDU-3 / IR-EDU-1 / IR-EDU-3）
     * 描述：选课结果事件推送到学分统计（内部）
     * 角色：内部服务
     * 关联：IR-EDU-1
     */
    Object createInternalEducationEnrollmentEvents(InternalEducationRequest request);

}
