package org.example.practice.practiceknowbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@SpringBootApplication(
        scanBasePackages = {"org.example.practice.practiceknowbox"},
        exclude = {DataSourceAutoConfiguration.class})
public class PracticeKnowbox {

    public static void main(String[] args) {
        SpringApplication.run(PracticeKnowbox.class, args);
    }
}
