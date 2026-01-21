package com.example.demosys.domain.education.service.impl;

import com.example.demosys.common.exception.BizException;
import com.example.demosys.domain.education.dto.CoursesResponse;
import com.example.demosys.domain.education.entity.Course;
import com.example.demosys.domain.education.mapper.CourseMapper;
import com.example.demosys.domain.education.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Override
    public CoursesResponse list(String keyword) {
        String kw = keyword == null ? null : keyword.trim();
        List<Course> rows = courseMapper.selectCourses(kw);
        if (rows == null || rows.isEmpty()) {
            CoursesResponse resp = new CoursesResponse();
            resp.setItems(Collections.emptyList());
            return resp;
        }

        List<CoursesResponse.CourseItem> items = new ArrayList<>();
        for (Course c : rows) {
            items.add(toItem(c));
        }

        CoursesResponse resp = new CoursesResponse();
        resp.setItems(items);
        return resp;
    }

    @Override
    public CoursesResponse.CourseItem getByCourseCode(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new BizException("courseCode 不能为空");
        }
        Course c = courseMapper.selectByCourseCode(courseCode.trim());
        if (c == null) return null;
        return toItem(c);
    }

    @Override
    public CoursesResponse.CourseItem getById(Long id) {
        if (id == null) throw new BizException("id 不能为空");
        Course c = courseMapper.selectById(id);
        if (c == null) return null;
        return toItem(c);
    }

    private CoursesResponse.CourseItem toItem(Course c) {
        CoursesResponse.CourseItem it = new CoursesResponse.CourseItem();
        it.setId(c.getId());
        it.setCourseCode(c.getCourseCode());
        it.setCourseName(c.getCourseName());
        it.setCredit(c.getCredit());
        return it;
    }
}
