package org.example.practice.practicecode.javalang.utils.thread.sync;

/**
 * 三、synchronized作用于同步代码块
 *
 * 使用同步代码块的方式对需要同步的代码进行包裹，这样就无需对整个方法进行同步操作了。 除了instance作为对象外，我们还可以使用this对象(代表当前实例)或者当前类的class对象作为锁。
 */
public class SynchronizedTest6 implements Runnable {
    static SynchronizedTest6 instance = new SynchronizedTest6();
    static int i = 0;

    @Override
    public void run() {
        // 省略其他耗时操作....
        // 使用同步代码块对变量i进行同步操作,锁对象为instance
        synchronized (instance) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }

        // 2. 使用 this
        /*synchronized(this){
            for(int j=0;j<10000;j++){
                i++;
            }
        }*/

        // 3. 使用当前类的 class 对象
        /*synchronized(SynchronizedTest6.class){
            for(int j=0;j<10000;j++){
                i++;
            }
        }*/
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
