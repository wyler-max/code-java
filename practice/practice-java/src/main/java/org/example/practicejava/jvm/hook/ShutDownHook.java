package org.example.practicejava.jvm.hook;

import org.junit.Test;

/**
 *
 */
public class ShutDownHook {
    public static void main(String[] args) {

    }

    @Test
    public void demo1() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutdown Hook is Running!");
            }
        });
        System.out.println("Application Terminnating....");
    }

    @Test
    public void demo2() {
        Runtime current = Runtime.getRuntime();
        current.addShutdownHook(new ThreadChild());
        for (int i = 0; i < 10; i++) {
            System.out.println("2 X " + i + " = " + 2*i);
        }
    }

    class ThreadChild extends Thread {
        @Override
        public void run() {
            System.out.println("In clean up code");
            System.out.println("In shutdown hook");
        }
    }
}
