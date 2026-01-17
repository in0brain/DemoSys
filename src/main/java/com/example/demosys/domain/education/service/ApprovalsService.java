package com.example.demosys.domain.education.service;

/**
 * ApprovalsService
 * 自动生成：培养模块 Service（资源：approvals）。
 */
public interface ApprovalsService {

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：待我审批的选课申请
     * 角色：导师/培养管理员
     * 关联：SR-EDU-2
     */
    Object listApprovalsEnrollmentsPending(java.util.Map<String, Object> params);

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：待我审批的培养计划
     * 角色：导师/培养管理员
     * 关联：SR-EDU-1
     */
    Object listApprovalsStudyPlansPending(java.util.Map<String, Object> params);

}
