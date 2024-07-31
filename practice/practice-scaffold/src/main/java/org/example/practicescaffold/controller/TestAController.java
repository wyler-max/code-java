package org.example.practicescaffold.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.example.practicescaffold.common.exception.ServiceException;
import org.example.practicescaffold.config.AConfig;
import org.example.practicescaffold.config.BConfig;
import org.example.practicescaffold.services.TestService;
import org.example.practicescaffold.services.enums.ActivityTypeEnum;
import org.example.practicescaffold.services.enums.UserErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAController {

    @Autowired
    private ServletRequest request;
    @Autowired
    private ServletResponse response;
    @Autowired
    private TestService testService;

    @Autowired
    private AConfig aConfig;
    @Autowired
    private BConfig bConfig;

    @Resource(name = "defaultThreadPool")
    private ThreadPoolTaskExecutor executor;

    @RequestMapping("/testA")
    public String testA() {
        System.out.println("testA");
        return "print testA";
    }

    @RequestMapping("/testB")
    public String testB() {
        System.out.println("testB");
        return "print testB";
    }

    @RequestMapping("/testC")
    public String testC() throws ServletException, IOException {
        System.out.println("testC");
        request.getRequestDispatcher("/testA").forward(request, response);
        return "redirect:/testA";
    }

    @RequestMapping("/testThread")
    public String testThread() throws Exception {
        System.out.println("testThread");

        System.out.println(new Date());
        int totalCount = 0, successCount = 0, failCount = 0;
        totalCount = 100;
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            Future<Integer> future = testService.exectuorService(i);
            futures.add(future);
        }
        // 查询任务执行的结果
        for (Future<?> future : futures) {
            while (true) {
                if (future.isDone() && !future.isCancelled()) {
                    Integer i = (Integer)future.get();
                    System.out.println("任务i=" + i + "获取完成!" + new Date());
                    successCount += i;
                    break;
                } else {
                    Thread.sleep(10);
                }
            }
        }
        System.out.println(new Date());
        failCount = totalCount - successCount;
        return String.format("total=%d, success=%d, fail=%d", totalCount, successCount, failCount);
    }

    @RequestMapping("/testEnum")
    public String testEnum() {
        System.out.println("testEnum");
        System.out.println(UserErrorCode.USER_A);
        System.out.println(UserErrorCode.USER_B);
        throw ServiceException.of(UserErrorCode.USER_A);
    }

    @RequestMapping("/testAConfig")
    public String testAConfig() {
        // return JsonUtil.toJson(aConfig);
        System.out.println(aConfig.getName());
        System.out.println(aConfig.getAge());
        System.out.println(aConfig.getAddr());
        System.out.println(aConfig.toString());
        return "JsonUtil.toJson(aConfig)";
    }

    @RequestMapping("/testBConfig")
    public String testBConfig() {
        // return JsonUtil.toJson(bConfig);
        System.out.println(bConfig.getName());
        System.out.println(bConfig.getAge());
        System.out.println(bConfig.getAddr());
        System.out.println(bConfig.toString());
        return "JsonUtil.toJson(bConfig)";
    }

    public static void main(String[] args) {
        byte b = 1;
        if (b == ActivityTypeEnum.TYPEA.getType()) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
    }
}
