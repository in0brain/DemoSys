package com.example.demosys.common.api;

/**
 * ApiException
 * 业务异常基类（用于 Service 层）
 */
public class ApiException extends RuntimeException {

    private final int code;

    public ApiException(String message) {
        super(message);
        this.code = ApiCode.BUSINESS_ERROR.getCode();
    }

    public ApiException(ApiCode code, String message) {
        super(message);
        this.code = code.getCode();
    }

    public int getCode() {
        return code;
    }
}
