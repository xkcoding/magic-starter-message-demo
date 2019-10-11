package com.xkcoding.magicstartermessagedemo.exception;

import com.xkcoding.magic.core.tool.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 异常拦截
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/10/11 15:15
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public String handlerAuthorizationException(ServiceException ex) {
        log.error("【拦截异常】", ex);
        return ex.getMessage();
    }
}
