package org.example.practice.practicespring.springbean;

import org.springframework.stereotype.Component;

/**
 * @author wangyulin
 * @date 2021/6/16
 */
@Component
public class X {
    private int id;

    public X() {
        System.out.println("X Constructor");
    }
}
