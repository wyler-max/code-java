package org.example.practice.practicespring.web;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.example.practice.commonutils.pojo.User;

import java.util.Optional;

/**
 * @author wangyulin
 * @date 2022/9/22
 */
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

    }

    public static void main(String[] args) {
        User u1 = new User();
        u1.setUserId(100);
        u1 = null;
        User u2 = new User();
        Optional.ofNullable(u1).ifPresent(x->u2.setUserId(x.getUserId()));
        System.out.println(u1);
        System.out.println(u2);
    }
}
