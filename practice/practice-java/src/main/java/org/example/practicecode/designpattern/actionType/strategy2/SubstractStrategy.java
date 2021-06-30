package org.example.practicecode.designpattern.actionType.strategy2;

/**
 * 策略实现类，减法
 */
public class SubstractStrategy implements Strategy {
    @Override
    public int calc(int num1, int num2) {
        return num1 - num2;
    }
}
