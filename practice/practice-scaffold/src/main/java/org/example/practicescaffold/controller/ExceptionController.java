package org.example.practicescaffold.controller;

import javax.annotation.Resource;

import org.example.practicescaffold.common.exception.ServiceException;
import org.example.practicescaffold.common.model.Response;
import org.example.practicescaffold.common.utils.ResponseUtil;
import org.example.practicescaffold.services.ExceptionService;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/exception")
@Slf4j
@Description(value = "测试异常处理")
public class ExceptionController {

    @Resource
    ExceptionService exceptionService;

    @RequestMapping("/test")
    public Response<String> testException() throws ServiceException {
        return ResponseUtil.makeSuccess("ok");
    }

    @RequestMapping("/test1")
    public Response<String> test1() {
        return ResponseUtil.makeSuccess(exceptionService.getTestExcept1());
    }

    @RequestMapping("/test2")
    public Response<String> test2() {
        return ResponseUtil.makeSuccess(exceptionService.getTestExcept2());
    }
}
