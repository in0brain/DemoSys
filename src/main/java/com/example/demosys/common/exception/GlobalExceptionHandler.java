package com.example.demosys.common.exception;

import com.example.demosys.common.api.ApiCode;
import com.example.demosys.common.api.ApiException;
import com.example.demosys.common.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public ApiResponse handleBiz(BizException e) {
        return ApiResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ApiResponse handleApi(ApiException e) {
        return ApiResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handleAny(Exception e) {
        log.error("Unhandled exception", e);
        return ApiResponse.fail(ApiCode.FAIL.getCode(), e.getMessage());
    }
}
