package org.example.springmvcstart.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
public class HandlerInterceptor1 implements HandlerInterceptor {

    /**
     * 1.预处理，controller方法执行前，
     * 2.可以使用request或者response跳转到指定的页面
     * 3.1.return true 放行，执行下一个拦截器，如果没有，执行controller中的方法
     * 3.2.return false不放行
     *
     * 何时调用：
     *  只要配置了都会调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor1 拦截器执行了...preHandle...");
        return true;
    }

    /**
     * 1.后处理方法，controller方法执行后，success.jsp执行之前
     * 2.可以使用request或者response跳转到指定的页面
     * 3.如果指定了跳转的页面，那么controller方法跳转的页面将不会显示
     *
     * 何时调用
     *  在拦截器链内所有拦截器返成功调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor1 拦截器执行了...postHandle...");
    }

    /**
     * 1.success.jsp页面执行后，该方法会执行
     * 2.request或者response不能再跳转页面了
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor1 拦截器执行了...afterCompletion...");
    }
}
