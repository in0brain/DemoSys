package com.example.demosys.domain.education.service;

import com.example.demosys.domain.education.dto.StudyPlansApproveRequest;
import com.example.demosys.domain.education.dto.StudyPlansRejectRequest;
import com.example.demosys.domain.education.dto.StudyPlansRequest;
import com.example.demosys.domain.education.dto.StudyPlansSubmitRequest;

/**
 * StudyPlanService
 * 自动生成：培养模块 Service（资源：study-plans）。
 */
public interface StudyPlanService {

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：我的培养计划列表（含版本/状态）
     * 角色：学生
     * 关联：SR-EDU-1
     */
    Object listStudyPlans(java.util.Map<String, Object> params);

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：新建培养计划（草稿）
     * 角色：学生
     * 关联：SR-EDU-1
     */
    Object createStudyPlans(StudyPlansRequest request);

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：编辑培养计划
     * 角色：学生
     * 关联：SR-EDU-1
     */
    Object updateStudyPlansById(StudyPlansRequest request);

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：审批通过（导师/学院）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-1
     */

    Object createStudyPlansByIdApprove(StudyPlansApproveRequest request);

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：退回修改（含意见）
     * 角色：导师/培养管理员
     * 关联：SR-EDU-1
     */
    Object createStudyPlansByIdReject(StudyPlansRejectRequest request);

    /**
     * 分组：3.1 培养方案与培养计划（SR-EDU-1）
     * 描述：提交培养计划审核
     * 角色：学生
     * 关联：SR-EDU-1
     */
    Object createStudyPlansByIdSubmit(StudyPlansSubmitRequest request);

}
