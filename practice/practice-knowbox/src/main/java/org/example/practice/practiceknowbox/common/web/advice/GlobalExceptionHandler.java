package org.example.practice.practiceknowbox.common.web.advice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.catalina.connector.ClientAbortException;
import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.excpetion.ServiceException;
import org.example.practice.practiceknowbox.common.model.Response;
import org.example.practice.practiceknowbox.common.util.ResponseUtil;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@RestControllerAdvice(basePackages = {"org.example.practice.practiceknowbox"})
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response<Void> processAllException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        if (e instanceof ServiceException) {
            ServiceException ex = (ServiceException) e;
            log.debug("业务处理异常: {}", ex.getMsg(), ex);
            if (ex.getCode() != 0) {
                return ResponseUtil.makeResponse(ex.getCode(), ex.getMsg());
            } else {
                return ResponseUtil.makeFail(ex.getMsg());
            }
        } else if (e instanceof HttpRequestMethodNotSupportedException
            || e instanceof HttpMediaTypeNotSupportedException || e instanceof TypeMismatchException
            || e instanceof HttpMessageNotReadableException || e instanceof MissingServletRequestPartException
            || e instanceof BindException || e instanceof NoHandlerFoundException) {// spring 默认的40x异常
            log.error("GlobalExceptionHandler", e);
            return ResponseUtil.makeError(ErrorCode.BAD_REQUEST);
        } else if (e instanceof ServletRequestBindingException) {
            log.info("ServletRequestBindingException", e);
            return ResponseUtil.makeError(ErrorCode.BAD_REQUEST);
        } else if (e instanceof MethodArgumentNotValidException) {
            log.info("MethodArgumentNotValidException", e);
            List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            String message = allErrors.get(allErrors.size() - 1).getDefaultMessage();
            return ResponseUtil.makeError(ErrorCode.BAD_REQUEST, message);
        } else if (e instanceof org.springframework.validation.BindException) {
            log.info("org.springframework.validation.BindException", e);
            List<ObjectError> allErrors =
                ((org.springframework.validation.BindException) e).getBindingResult().getAllErrors();
            String message = allErrors.get(allErrors.size() - 1).getDefaultMessage();
            return ResponseUtil.makeError(ErrorCode.BAD_REQUEST, message);
        } else if (e instanceof ConstraintViolationException) {
            log.info("ConstraintViolationException", e);
            return ResponseUtil.makeError(ErrorCode.BAD_REQUEST, e.getMessage());
        } else if (e instanceof ClientAbortException) {
            log.info("ClientAbortException", e);
            return ResponseUtil.makeError(ErrorCode.CLIENT_ABORT);
        } else if (e instanceof BeanCreationNotAllowedException) {
            log.info("BeanCreationNotAllowedException", e);
            return ResponseUtil.makeError(ErrorCode.RESTART);
        } else {
            log.error("GlobalExceptionHandler", e);
            return ResponseUtil.makeError(ErrorCode.UNHANDLER_EXCEPTION);
        }

    }
}
