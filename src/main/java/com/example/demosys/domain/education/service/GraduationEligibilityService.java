package com.example.demosys.domain.education.service;

/**
 * GraduationEligibilityService
 * 自动生成：培养模块 Service（资源：graduation-eligibility）。
 */
public interface GraduationEligibilityService {

    /**
     * 分组：3.3 学分统计与毕业资格（SR-EDU-3 / IR-EDU-1 / IR-EDU-3）
     * 描述：我的毕业资格状态（达标/差项）
     * 角色：学生
     * 关联：SR-EDU-3, IR-EDU-3
     */
    Object listGraduationEligibilityMe(java.util.Map<String, Object> params);

    /**
     * 分组：3.3 学分统计与毕业资格（SR-EDU-3 / IR-EDU-1 / IR-EDU-3）
     * 描述：指定学生毕业资格
     * 角色：导师/培养管理员
     * 关联：SR-EDU-3, IR-EDU-3
     */
    Object getGraduationEligibilityStudentsById(java.util.Map<String, Object> params);

}
