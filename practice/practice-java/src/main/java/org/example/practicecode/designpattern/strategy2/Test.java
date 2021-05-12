package org.example.practicecode.designpattern.strategy2;

import com.example.designpattern.strategy2.AddStrategy;
import com.example.designpattern.strategy2.Environment;
import com.example.designpattern.strategy2.SubstractStrategy;

/**
 * 策略模式测试类
 */
public class Test {
    public static void main(String[] args) {

        Environment env = new Environment();
        AddStrategy addStrategy = new AddStrategy();
        SubstractStrategy substractStrategy = new SubstractStrategy();

        env.setStrategy(addStrategy);
        System.out.println(env.calculate(8, 5));

        env.setStrategy(substractStrategy);
        System.out.println(env.calculate(8, 5));
    }
}
