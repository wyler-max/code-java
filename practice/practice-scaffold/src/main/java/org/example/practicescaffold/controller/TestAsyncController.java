package org.example.practicescaffold.controller;

import org.example.practicescaffold.services.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/async")
public class TestAsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/sync")
    public String sync() {
        System.out.println("sync");
        asyncService.doSync();
        return "sync";
    }

    @RequestMapping("/async")
    public String async() {
        System.out.println("async");
        asyncService.doAsync();
        return "async";
    }

    @RequestMapping("/async2")
    public String async2() {
        System.out.println("async2");
        asyncService.doAsync2();
        return "async2";
    }
}
