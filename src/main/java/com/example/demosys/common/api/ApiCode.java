package com.example.demosys.common.api;

/**
 * ApiCode
 * 统一返回码定义
 */
public enum ApiCode {

    SUCCESS(0, "success"),
    FAIL(1, "fail"),

    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not found"),

    VALIDATION_ERROR(1001, "validation error"),
    BUSINESS_ERROR(1002, "business error");

    private final int code;
    private final String message;

    ApiCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
