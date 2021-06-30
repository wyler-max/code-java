package org.example.practicecode.designpattern.actionType.strategy2;

/**
 * 策略实现类，加法
 */
public class AddStrategy implements Strategy {
    @Override
    public int calc(int num1, int num2) {
        return num1 + num2;
    }
}
