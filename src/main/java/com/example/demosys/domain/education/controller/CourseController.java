package com.example.demosys.domain.education.controller;

import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.education.dto.CoursesResponse;
import com.example.demosys.domain.education.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：可选课程列表（筛选：关键字）
     * 角色：学生
     * 关联：SR-EDU-2
     *
     * 说明（SR 极简版）：
     * - 暂不处理 page/pageSize（前端可一次性加载）
     * - filters 里只读取 keyword，其余忽略
     */
    @GetMapping("/courses")
    public ApiResponse listCourses(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Map<String, String> filters
    ) {
        String keyword = null;
        if (filters != null) {
            keyword = filters.get("keyword");
            if (keyword == null || keyword.isBlank()) {
                // 兼容前端可能传 name/code
                keyword = filters.getOrDefault("name", filters.get("code"));
            }
        }

        return ApiResponse.ok(courseService.list(keyword));
    }

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：课程详情（学分等基础信息）
     * 角色：登录用户
     * 关联：SR-EDU-2
     *
     * 说明：
     * - SR 阶段只返回基础字段
     */
    @GetMapping("/courses/{courseId}")
    public ApiResponse getCoursesBy(
            @PathVariable("courseId") String courseId
    ) {
        // 兼容：courseId 可能是数字 id，也可能是课程编号
        CoursesResponse.CourseItem item;

        if (courseId.matches("\\d+")) {
            item = courseService.getById(Long.valueOf(courseId));
        } else {
            item = courseService.getByCourseCode(courseId);
        }

        return ApiResponse.ok(item);
    }
}
