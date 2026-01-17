package com.example.demosys.domain.education.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * CourseController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/education")
public class CourseController {

    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：可选课程列表（筛选：学期/学院/时间/关键字）
     * 角色：学生
     * 关联：SR-EDU-2
     */
    @GetMapping("/courses")
    public ApiResponse listCourses(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：3.2 课程与选课（SR-EDU-2）
     * 描述：课程详情（容量、时间、学分）
     * 角色：登录用户
     * 关联：SR-EDU-2
     */
    @GetMapping("/courses/{courseId}")
    public ApiResponse getCoursesBy(@PathVariable("courseId") String courseId) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
