package org.example.practice.practicespring.springcontext;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 获取application.properties中的配置
 *
 * getProperty
 */
@Component
public class SelfEnvironmentAware implements EnvironmentAware {

    private Environment environment;

    // 当实现了 EnvironmentAware 接口，并重写该方法后，在启动时就能获取到 application.properties中的配置
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        System.out.println(environment.getProperty("spring.project.env"));
    }
}
