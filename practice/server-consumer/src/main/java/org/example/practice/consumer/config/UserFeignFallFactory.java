package org.example.practice.consumer.config;

import org.example.practice.consumer.feignclient.Provider1UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * 为指定的 @FeignClient 接口定义一个 fallbackFactory。
 *
 * fallbackFactory 实现由 @FeignClient 标记的接口的错误处理工厂实例。
 */
@Component
public class UserFeignFallFactory implements FallbackFactory<Provider1UserClient> {

    @Autowired
    private Provider1UserFeignFallBack userFeignFallBack;

    @Override
    public Provider1UserClient create(Throwable cause) {
        // 可以获取比较详细的信息
        // cause.printStackTrace();
        return userFeignFallBack;
    }
}
