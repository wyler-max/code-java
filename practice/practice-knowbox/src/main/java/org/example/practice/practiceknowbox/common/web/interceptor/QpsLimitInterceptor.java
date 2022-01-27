package org.example.practice.practiceknowbox.common.web.interceptor;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.practice.practiceknowbox.common.cache.CacheKey;
import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.util.HttpUtil;
import org.example.practice.practiceknowbox.common.web.annotation.QpsLimiter;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yijiu.chen
 * @date 2020/05/15
 */
@Slf4j
public class QpsLimitInterceptor extends HandlerInterceptorAdapter implements Ordered {

    @Autowired
    private RedissonClient redissonClient;

    /* (non-Javadoc)
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 2;// log&login 后面
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        // 只支持方法级别
        QpsLimiter annotation = method.getAnnotation(QpsLimiter.class);
        if (annotation == null) {
            return true;
        }

        // 计算用户的key，userId + 时间范围
        String name = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        String key = CacheKey.buildKey("API", "qps", name + ":" + System.currentTimeMillis() / 1000L);
        try {
            // 获取次数
            RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
            int l = (int)atomicLong.incrementAndGet();
            atomicLong.expire(1, TimeUnit.SECONDS);
            // 小于阈值
            if (l > annotation.qps()) {
                // 若超过返回操作频繁
                HttpUtil.flushResponse(response, ErrorCode.QPS_LIMIT, HttpStatus.TOO_MANY_REQUESTS.value());
                return false;
            }
        } catch (Exception e) {
            log.error("redis连接异常key:" + key + "", e);
        }

        return true;

    }

}
