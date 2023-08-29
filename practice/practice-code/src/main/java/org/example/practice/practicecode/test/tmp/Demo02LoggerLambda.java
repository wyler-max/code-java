package org.example.practice.practicecode.test.tmp;

public class Demo02LoggerLambda {
    public static void show(int i, Demo02Logger logger) {
        if (i == 1) {
            System.out.println(logger.show());
        }
    }

    public static void main(String[] args) {
        String message1 = "Hello";
        String message2 = "world";
        String message3 = "world";
        show(1, () -> {
            return message1 + message2 + message3;
        });
        System.out.println("mark1");
        show(1, new Demo02Logger() {
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
    }
}
