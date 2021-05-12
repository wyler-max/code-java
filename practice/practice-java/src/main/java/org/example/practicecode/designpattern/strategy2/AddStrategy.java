package org.example.practicecode.designpattern.strategy2;

/**
 * 策略角色实现类
 */
public class AddStrategy implements Strategy {
    @Override
    public int calc(int num1, int num2) {
        return num1 + num2;
    }
}
