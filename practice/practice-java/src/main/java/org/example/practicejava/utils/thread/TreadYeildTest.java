package org.example.practicejava.utils.thread;

/**
 * 测试 Thread.Yeild()
 */
public class TreadYeildTest extends Thread {

    public TreadYeildTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("===" + this.getName() + "===, i=" + i);
            // 当该进程执行到 i==30时，让出cpu；所有线程重新抢占cpu资源
            if (i == 30) {
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        TreadYeildTest t1 = new TreadYeildTest("jack");
        TreadYeildTest t2 = new TreadYeildTest("tom");
        t1.start();
        t2.start();
    }
}
