package com.example.demosys.domain.admissions.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * PublicadmissionsofferController
 * <p>
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成的 Controller 骨架（按模块/路径分组）。
 * 请在实现时：补充 DTO、鉴权（@PreAuthorize 或自定义注解）、校验（@Validated）与 Service 调用。
 * </p>
 */
@RestController
@RequestMapping("/public")
public class PublicadmissionsofferController {

    /**
     * 分组：公众查询（无需登录或弱鉴权）
     * 描述：公示查询（按考号/姓名）
     * 角色：公众/弱鉴权
     * 关联：IR-ADM-3
     */
    @GetMapping("/admissions/offers")
    public ApiResponse listAdmissionsOffers(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) java.util.Map<String, String> filters) {
        // TODO: implement
        return ApiResponse.ok(null);
    }

}
