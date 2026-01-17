package com.example.demosys.domain.education.service;

/**
 * CourseService
 * 自动生成：培养模块 Service（资源：courses）。
 */
public interface CourseService {

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：可选课程列表（筛选：学期/学院/时间/关键字）
     * 角色：学生
     * 关联：SR-EDU-2
     */
    Object listCourses(java.util.Map<String, Object> params);

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：课程详情（容量、时间、学分）
     * 角色：登录用户
     * 关联：SR-EDU-2
     */
    Object getCoursesById(java.util.Map<String, Object> params);

}
