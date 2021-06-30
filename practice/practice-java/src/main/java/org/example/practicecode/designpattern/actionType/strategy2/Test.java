package org.example.practicecode.designpattern.actionType.strategy2;

/**
 * 策略模式，可以在运行时根据需要修改类的行为或算法。
 *
 * 在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。</br>
 * 策略对象改变 context 对象的执行算法/逻辑
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
