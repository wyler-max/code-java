package org.example.practicescaffold.redis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.example.practicescaffold.redis")
@Import(JedisConfig.class)
@PropertySource("classpath:redis.properties")
public class SpringConfiguration {
}
