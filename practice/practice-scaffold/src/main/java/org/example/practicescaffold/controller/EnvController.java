package org.example.practicescaffold.controller;

import javax.annotation.Resource;

import org.example.practicescaffold.common.exception.ServiceException;
import org.example.practicescaffold.common.model.Response;
import org.example.practicescaffold.common.utils.ResponseUtil;
import org.example.practicescaffold.services.ExceptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/env")
@Slf4j
public class EnvController {

    @Resource
    ExceptionService exception;

    @Value("${spring.profiles.active}")
    private String tmp;

    @RequestMapping("/env")
    public String env() {
        return tmp;
    }

    @RequestMapping("/testException")
    public Response<String> testException() throws ServiceException {
        return ResponseUtil.makeSuccess("ok");
    }

    @RequestMapping("/except/1")
    public Response<String> except1() {
        return ResponseUtil.makeSuccess(exception.getExcept1());
    }

    @RequestMapping("/except/2")
    public Response<String> except2() {
        return ResponseUtil.makeSuccess(exception.getExcept2());
    }
}
