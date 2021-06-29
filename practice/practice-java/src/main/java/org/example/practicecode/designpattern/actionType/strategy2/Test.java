package org.example.practicecode.designpattern.actionType.strategy2;

/**
 * 策略模式测试类
 */
public class Test {
    public static void main(String[] args) {

        Environment env = new Environment();

        env.setStrategy(new AddStrategy());
        System.out.println(env.calculate(8, 5));

        env.setStrategy(new SubstractStrategy());
        System.out.println(env.calculate(8, 5));
    }
}
