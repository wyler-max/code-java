package org.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangyulin
 * @date 2023/7/7
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ServerConsumer1 {

    public static void main(String[] args) {
        SpringApplication.run(ServerConsumer1.class, args);
    }
}