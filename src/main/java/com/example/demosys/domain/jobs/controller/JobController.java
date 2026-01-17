package com.example.demosys.domain.jobs.controller;

import com.example.demosys.common.api.ApiResponse;
import com.example.demosys.domain.jobs.service.JobService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * JobController
 * 自动生成：/jobs 接口 Controller 骨架（对齐接口清单_RESTful_v1_对齐IR.xlsx）。
 *
 * 注意：
 * - 当前返回使用非泛型 ApiResponse（与你项目 common/api/ApiResponse.java 的常见定义一致）。
 * - 具体参数/校验/权限注解请按接口清单备注与安全方案补充。
 */
@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;



}
