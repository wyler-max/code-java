package org.example.practicescaffold.web.advice;

import java.util.List;

import org.example.practicescaffold.common.exception.ServiceException;
import org.example.practicescaffold.common.model.Response;
import org.example.practicescaffold.common.model.errorcode.CommErrorCode;
import org.example.practicescaffold.common.utils.ResponseUtil;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 *
 * 完整链路： 调用：filter -> interceptor -> ControllerAdvice -> aspect -> controller 返回：controller -> aspect ->
 * ControllerAdvice -> interceptor -> filter
 *
 * 功能：捕获来自 controller、aspect 的异常
 */
@RestControllerAdvice(basePackages = "org.example")
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    public Response<Object> exceptionHandler(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException ex = (ServiceException)e;
            return ResponseUtil.makeResponse(ex.getErrorCode().getGlobalErrorCode(), ex.getMessage(), ex.getResult());
        } else if (e instanceof MethodArgumentNotValidException) {
            log.info("GlobalExceptionHandler", e);// INFO级别
            List<ObjectError> allErrors = ((MethodArgumentNotValidException)e).getBindingResult().getAllErrors();
            String message = allErrors.get(allErrors.size() - 1).getDefaultMessage();
            return ResponseUtil.makeFail(CommErrorCode.E400_BAD_REQUEST, message);
        } else {
            log.error("GlobalExceptionHandler", e);
            return ResponseUtil.makeFail(CommErrorCode.UNKNOWN);
        }
    }

}
