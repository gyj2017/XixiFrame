package com.gyj.api.common.exception;

import com.gyj.api.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public AjaxResult handler(RuntimeException e) {
        log.error("运行时异常--------{}" + e.getMessage());
        return AjaxResult.error(e.getMessage());
    }

}
