package com.example.demosys.domain.education.mapper;

import com.example.demosys.domain.education.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    /**
     * 课程列表（keyword 可为空：返回全量；不为空：按 code/name 模糊）
     */
    List<Course> selectCourses(@Param("keyword") String keyword);

    Course selectByCourseCode(@Param("courseCode") String courseCode);

    Course selectById(@Param("id") Long id);

    List<Course> selectByCourseCodes(@Param("courseCodes") List<String> courseCodes);

    int insertCourse(Course course);

    int updateCourseById(Course course);
}
