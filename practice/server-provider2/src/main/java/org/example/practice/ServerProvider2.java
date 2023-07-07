package org.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangyulin
 * @date 2023/7/7
 */
@SpringBootApplication
@EnableEurekaClient
public class ServerProvider2 {

    public static void main(String[] args) {
        SpringApplication.run(ServerProvider2.class, args);
    }
}