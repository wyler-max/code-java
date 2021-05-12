package org.example.springbootstart.controller;

import org.example.springbootstart.common.exception.ServiceException;
import org.example.springbootstart.common.model.Response;
import org.example.springbootstart.common.model.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env")
public class EnvController {

    @Value("${spring.profiles.active}")
    private String tmp;

    @RequestMapping("/env")
    public String env() {
        return tmp;
    }

    @RequestMapping("/testException")
    public Response<String> testException() throws ServiceException {
        //throw new ServiceException("测试全局异常捕获");
        throw new ServiceException(ErrorCode.USER_NO_LOGIN.getCode(), "测试全局异常捕获");
    }

}
