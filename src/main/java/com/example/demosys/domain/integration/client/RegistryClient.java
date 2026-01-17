package com.example.demosys.domain.integration.client;

import org.springframework.web.bind.annotation.*;
import com.example.demosys.common.api.ApiResponse;

/**
 * RegistryClient
 * 外部系统调用契约（本系统作为调用方）。
 * 由《接口清单_RESTful_v1_对齐IR.xlsx》自动生成。
 *
 * 说明：你可以用 OpenFeign/RestTemplate/WebClient 实现，此处仅提供方法签名骨架。
 */
public interface RegistryClient {

/**
 * 描述：答辩结果回写学籍/档案系统
 * 备注：外部端点：本系统作为调用方
 */
@PostMapping("/registry/graduation/results")
ApiResponse createRegistryGraduationResults(@RequestBody Object request);

}
