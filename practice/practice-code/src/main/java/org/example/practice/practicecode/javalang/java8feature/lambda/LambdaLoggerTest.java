package org.example.practice.practicecode.javalang.java8feature.lambda;

public class LambdaLoggerTest {
    public static void show(int i, LambdaLogger logger) {
        if (i == 1) {
            System.out.println(logger.show());
        }
    }

    public static void main(String[] args) {
        String message1 = "Hello";
        String message2 = "world1";
        String message3 = "world2";
        show(1, () -> {
            return message1 + message2 + message3;
        });
        System.out.println("mark1");
        show(1, new LambdaLogger() {
            @Override
            public String show() {
                return message1 + message2 + message3;
            }
        });
        System.out.println("mark2");
        show(2, () -> {
            System.out.println("不满足条件，不执行！");
            return message1 + message2 + message3;
        });
        System.out.println("mark end");
    }
}
