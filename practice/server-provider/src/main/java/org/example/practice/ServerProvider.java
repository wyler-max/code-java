package org.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServerProvider {
    public static void main(String[] args) {
        SpringApplication.run(ServerProvider.class, args);
    }
}
