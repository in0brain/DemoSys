package com.example.demosys.domain.admissions.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * InterviewScoreController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/admissions")
public class InterviewScoreController {

    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：导出成绩汇总（Excel/PDF）
     * 角色：招生管理员
     * 关联：SR-ADM-1
     */
    @GetMapping("/interview-scores/export")
    public ApiResponse listInterviewScoresExport(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：批量导入成绩（Excel）
     * 角色：招生管理员
     * 关联：SR-ADM-1
     * 备注：建议：返回 jobId，GET /jobs/{jobId} 查询导入结果
     */
    @PostMapping("/interview-scores/import")
    public ApiResponse createInterviewScoresImport(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：2.1 考生与复试成绩（SR-ADM-1）
     * 描述：成绩锁定（截止后只读）
     * 角色：招生管理员/更高权限
     * 关联：SR-ADM-1
     */
    @PostMapping("/interview-scores/lock")
    public ApiResponse createInterviewScoresLock(@RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
