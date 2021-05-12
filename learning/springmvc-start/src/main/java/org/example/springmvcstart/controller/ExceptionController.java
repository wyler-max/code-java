package org.example.springmvcstart.controller;

import org.example.springmvcstart.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 7、异常处理控制器
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("testException 执行了...");

        try {
            int a = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("查询用户时出错了...");
        }
        return "success";
    }
}
