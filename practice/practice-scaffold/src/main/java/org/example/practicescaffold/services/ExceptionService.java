package org.example.practicescaffold.services;

import org.example.practicescaffold.common.exception.ServiceException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExceptionService {

    public String getExcept1() {
        return "success";
    }

    public String getExcept2() {
        throw ServiceException.ofSuccess("success");
    }
}
