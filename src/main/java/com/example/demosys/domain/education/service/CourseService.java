package com.example.demosys.domain.education.service;

import com.example.demosys.domain.education.dto.CoursesResponse;

public interface CourseService {

    /**
     * 课程列表（支持 keyword：按 code/name 模糊）
     */
    CoursesResponse list(String keyword);

    /**
     * 可选：按课程编号获取（后续补齐 courseName/credit 用）
     */
    CoursesResponse.CourseItem getByCourseCode(String courseCode);

    /**
     * 可选：按 id 获取
     */
    CoursesResponse.CourseItem getById(Long id);
}
