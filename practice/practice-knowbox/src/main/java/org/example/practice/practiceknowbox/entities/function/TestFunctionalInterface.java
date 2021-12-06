package org.example.practice.practiceknowbox.entities.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * 测试函数式接口
 */
public class TestFunctionalInterface {

    /**
     * 自定义函数
     */
    @Test
    public void testFunction() {
        Function<Integer, Integer> function1 = (x) -> x + 2;
        Function<Integer, Integer> function2 = (x) -> x + 3;
        System.out.println(function1.apply(5));
        System.out.println(function1.andThen(function2).apply(4));
    }

    /**
     * 自定义回调函数
     */
    @Test
    public void testSupplier() {
        Supplier<String> supplier1 = () -> "Test Supplier";
        System.out.println(supplier1.get());

        Supplier<Integer> supplier2 = () -> {
            return 2;
        };
        System.out.println(supplier2.get());
    }

    /**
     * 自定义条件函数
     */
    @Test
    public void testPredicate() {
        String str = "qaq";
        Predicate<String> predicate1 = (x) -> x.startsWith("qaq");
        System.out.println(predicate1.test(str));
    }

    /**
     * 自定义消费函数
     */
    @Test
    public void testConsumer() {
        String str = "qaq";
        Consumer<String> consumer1 = (x) -> System.out.print(x);
        Consumer<String> consumer2 = (x) -> System.out.println(" after comsumer1");
        consumer1.andThen(consumer2).accept(str);
    }

    @Test
    public void testCustomFunctional() {
        // 16进制转10进制函数
        CustomFunctional<String, Integer> customFunctional = (x) -> Integer.parseInt(x, 16);
        System.out.println(customFunctional.doAcction("12"));
    }
}
