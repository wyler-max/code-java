package org.example.springmvcstart.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 预处理方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor 拦截器执行了...preHandle...");

        // 如果是登录页面则放行
        if (request.getRequestURL().indexOf("/login")>=0) {
            System.out.println("LoginInterceptor：当前为登录页面，放行");
            return true;
        }
        HttpSession session = request.getSession();
        // 如果用户已登录也放行
        if (session.getAttribute("STUDENT_SESSION")!=null) {
            System.out.println("LoginInterceptor：用户已登录，放行");
            return true;
        }
        // 用户没有登录跳转到登录页面
        System.out.println("LoginInterceptor：用户未登录，跳转到登录页面");
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        return false;
    }

    /**
     * 后处理方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginInterceptor 拦截器执行了...postHandle...");
    }

    /**
     * 最终处理方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginInterceptor 拦截器执行了...afterCompletion...");
    }
}
