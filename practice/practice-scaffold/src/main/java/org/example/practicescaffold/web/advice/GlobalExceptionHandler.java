package org.example.practicescaffold.web.advice;

import org.example.practicescaffold.common.exception.ServiceException;
import org.example.practicescaffold.common.utils.ResponseUtil;
import org.example.practicescaffold.common.model.Response;
import org.example.practicescaffold.common.model.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 全局异常处理器
 *
 * 完整链路：
 * 调用：filter -> interceptor -> ControllerAdvice -> aspect -> controller
 * 返回：controller -> aspect -> ControllerAdvice -> interceptor -> filter
 *
 * 功能：捕获来自 controller、aspect 的异常
 */
@RestControllerAdvice(basePackages = "com.example")
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    public Response<Void> exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        if (e instanceof ServiceException) {
            ServiceException ex = (ServiceException) e;
            log.debug("业务处理异常: {}", ex.getMsg(), ex);
            if (ex.getCode() != 0) {
                return ResponseUtil.makeResponse(ex.getCode(), ex.getMsg());
            } else {
                return ResponseUtil.makeSuccess(ex.getMsg());
            }
        } else if (e instanceof MethodArgumentNotValidException) {
            log.info("GlobalExceptionHandler", e);// INFO级别
            List<ObjectError> allErrors = ((MethodArgumentNotValidException)e).getBindingResult().getAllErrors();
            String message = allErrors.get(allErrors.size() - 1).getDefaultMessage();
            return ResponseUtil.makeFail(ErrorCode.E400_BAD_REQUEST, message);
        } else {
            log.error("GlobalExceptionHandler", e);
            return ResponseUtil.makeFail(ErrorCode.UNKNOWN);
        }
    }

}
