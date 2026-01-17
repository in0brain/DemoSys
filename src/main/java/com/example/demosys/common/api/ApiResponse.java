package com.example.demosys.common.api;

import lombok.Data;

/**
 * ApiResponse
 * 统一接口返回对象（非泛型版本）
 *
 * 设计目标：
 * - 简单
 * - 不引入泛型，避免你当前项目的大规模改动
 * - 与 Controller 中 ApiResponse.ok(data) 用法完全一致
 */
@Data
public class ApiResponse {

    /** 返回码：0 表示成功，其它表示失败 */
    private int code;

    /** 提示信息 */
    private String message;

    /** 实际返回数据 */
    private Object data;

    /* ================== 静态工厂方法 ================== */

    public static ApiResponse ok() {
        ApiResponse r = new ApiResponse();
        r.code = ApiCode.SUCCESS.getCode();
        r.message = ApiCode.SUCCESS.getMessage();
        r.data = null;
        return r;
    }

    public static ApiResponse ok(Object data) {
        ApiResponse r = new ApiResponse();
        r.code = ApiCode.SUCCESS.getCode();
        r.message = ApiCode.SUCCESS.getMessage();
        r.data = data;
        return r;
    }

    public static ApiResponse fail(String message) {
        ApiResponse r = new ApiResponse();
        r.code = ApiCode.FAIL.getCode();
        r.message = message;
        r.data = null;
        return r;
    }

    public static ApiResponse fail(int code, String message) {
        ApiResponse r = new ApiResponse();
        r.code = code;
        r.message = message;
        r.data = null;
        return r;
    }
}
