package org.example.practice.practicespringbootstart.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangyulin
 * @date 2023/8/9
 */
@EnableEurekaClient
@SpringBootApplication
public class PracticeSpringbootStartApp {

    public static void main(String[] args) {
        SpringApplication.run(PracticeSpringbootStartApp.class, args);
    }
}
