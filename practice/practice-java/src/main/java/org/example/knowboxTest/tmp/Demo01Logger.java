package org.example.knowboxTest.tmp;

public class Demo01Logger {
    public static void show(int i, String message) {
        if (i == 1) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        String message1 = "Hello";
        String message2 = "world";
        String message3 = "world";
        show(1, message1 + message2 + message3);
        show(2, message1 + message2 + message3);
    }
}
