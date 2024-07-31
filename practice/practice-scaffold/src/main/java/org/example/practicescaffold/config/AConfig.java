package org.example.practicescaffold.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author wangyulin
 * @date 2024/7/30
 */
@Configuration
@ConfigurationProperties(prefix = "usera")
@Data
public class AConfig {
    private String name;
    private Integer age;
    private String addr;
}
