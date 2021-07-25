package org.example.practicescaffold.config;

import java.util.Objects;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * PropertySource注解不支持yaml文件加载 注入yaml类型文件的两种方式： 1、将文件命名为 application-xxx.yaml，主配置文件中添加 spring.profiles.include=xxx
 * 2、手动注入自定义yaml类型文件，本文件实现
 */
@Configuration
public class SpringBootConfiguration {

    // 加载YML格式自定义配置文件
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("yaml-test.yaml"));
        configurer.setProperties(Objects.requireNonNull(yaml.getObject()));
        return configurer;
    }
}
