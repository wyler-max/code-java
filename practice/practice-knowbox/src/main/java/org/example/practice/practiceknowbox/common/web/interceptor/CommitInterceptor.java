package org.example.practice.practiceknowbox.common.web.interceptor;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.practice.practiceknowbox.common.cache.CacheKey;
import org.example.practice.practiceknowbox.common.cache.annotation.MultiCommitCache;
import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.model.User;
import org.example.practice.practiceknowbox.common.model.UserContext;
import org.example.practice.practiceknowbox.common.util.HttpUtil;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 防重复提交拦截器
 *
 * @author zhangshuai
 * @date 2020/4/30 2:28 下午
 */
@Slf4j
public class CommitInterceptor extends HandlerInterceptorAdapter implements Ordered {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        // 只支持方法级别
        MultiCommitCache annotation = method.getAnnotation(MultiCommitCache.class);
        if (annotation == null) {
            return true;
        }
        User userInfo = UserContext.getUserInfo();
        if (userInfo != null) {
            // 计算用户的key，userId + 时间范围
            String name = method.getDeclaringClass().getSimpleName() + "." + method.getName();
            String key = CacheKey.buildKey("API", "limit",
                userInfo.getId() + ":" + name + ":" + LocalDateTime.now().getSecond() / annotation.delta());
            try {
                // 获取次数
                RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
                int l = (int)atomicLong.incrementAndGet();
                atomicLong.expire(annotation.delta(), TimeUnit.SECONDS);
                // 小于阈值
                if (l > annotation.threshold()) {
                    // 若超过返回操作频繁
                    HttpUtil.flushResponse(response, ErrorCode.TOO_FAST, HttpStatus.OK.value());
                    return false;
                }
            } catch (Exception e) {
                log.error("redis连接异常key:" + key + "", e);
            }
        }
        return true;
    }

    @Override
    public int getOrder() {
        // 在最后面
        return HIGHEST_PRECEDENCE + 3;
    }
}
