package com.example.demosys.domain.education.service;

/**
 * CrossSchoolService
 * 自动生成：培养模块 Service（资源：cross-school）。
 */
public interface CrossSchoolService {

    /**
     * 分组：3.4 跨学院课程规则（IR-EDU-2）
     * 描述：跨院认定规则列表
     * 角色：培养管理员
     * 关联：IR-EDU-2
     */
    Object listCrossSchoolRules(java.util.Map<String, Object> params);

    /**
     * 分组：3.4 跨学院课程规则（IR-EDU-2）
     * 描述：规则详情（替代/认定）
     * 角色：培养管理员
     * 关联：IR-EDU-2
     */
    Object getCrossSchoolRulesById(java.util.Map<String, Object> params);

}
