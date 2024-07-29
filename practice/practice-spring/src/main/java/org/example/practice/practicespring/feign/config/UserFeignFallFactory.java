package org.example.practice.practicespring.feign.config;

import org.example.practice.practicespring.feign.Provider1Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 为指定的 @FeignClient 接口定义一个 fallbackFactory。
 *
 * fallbackFactory 实现由 @FeignClient 标记的接口的错误处理工厂实例。
 */
@Component
public class UserFeignFallFactory implements FallbackFactory<Provider1Client> {

    @Autowired
    private Provider1FeignFallBack userFeignFallBack;

    @Override
    public Provider1Client create(Throwable cause) {
        // 可以获取比较详细的信息
        // cause.printStackTrace();
        return userFeignFallBack;
    }
}
