package com.example.demosys.domain.defense.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * ReviewController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/defense")
public class ReviewController {

    /**
     * 分组：4.2 盲审任务与专家评阅（SR-DEF-2 / IR-DEF-2）
     * 描述：专家端：我收到的评阅任务
     * 角色：校外专家
     * 关联：IR-DEF-2
     */
    @GetMapping("/expert/reviews")
    public ApiResponse listReviews(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：4.2 盲审任务与专家评阅（SR-DEF-2 / IR-DEF-2）
     * 描述：专家端：评阅任务详情（匿名论文）
     * 角色：校外专家
     * 关联：IR-DEF-2
     */
    @GetMapping("/expert/reviews/{reviewId}")
    public ApiResponse getReviewsBy(@PathVariable("reviewId") String reviewId) {
        // TODO: implement
        return ApiResponse.ok(null);
    }
    /**
     * 分组：4.2 盲审任务与专家评阅（SR-DEF-2 / IR-DEF-2）
     * 描述：专家端：提交评阅表/附件
     * 角色：校外专家
     * 关联：IR-DEF-2
     * 备注：专家访问可选：临时账号 token 或一次性签名链接 /expert/entry?token=xxx
     */
    @PostMapping("/expert/reviews/{reviewId}/submit")
    public ApiResponse createReviewsBySubmit(@PathVariable("reviewId") String reviewId, @RequestBody Object request) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
