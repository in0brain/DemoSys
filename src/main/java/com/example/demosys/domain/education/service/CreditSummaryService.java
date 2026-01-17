package com.example.demosys.domain.education.service;

/**
 * CreditSummaryService
 * 自动生成：培养模块 Service（资源：credits）。
 */
public interface CreditSummaryService {

    /**
     * 分组：3.3 学分统计与毕业资格（SR-EDU-3 / IR-EDU-1 / IR-EDU-3）
     * 描述：我的学分进度（按必修/选修/环节）
     * 角色：学生
     * 关联：SR-EDU-3
     */
    Object listCreditsMe(java.util.Map<String, Object> params);

    /**
     * 分组：3.3 学分统计与毕业资格（SR-EDU-3 / IR-EDU-1 / IR-EDU-3）
     * 描述：指定学生学分进度
     * 角色：导师/培养管理员
     * 关联：SR-EDU-3
     */
    Object getCreditsStudentsById(java.util.Map<String, Object> params);

}
