package org.example.practicescaffold.services;

import org.example.practicescaffold.common.exception.ServiceException;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Description(value = "mock一个会抛出异常的服务")
public class ExceptionService {

    public String getTestExcept1() {
        return "success";
    }

    public String getTestExcept2() {
        throw ServiceException.ofSuccess("success");
    }
}
