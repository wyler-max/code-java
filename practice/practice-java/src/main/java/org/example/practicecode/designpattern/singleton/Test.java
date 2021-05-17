package org.example.practicecode.designpattern.singleton;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        int size = 100;
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                System.out.println(SingleObject1LazyUnSafe.getInstance().hashCode());
                // System.out.println(SingleObject2Lazy.getInstance().hashCode());
                // System.out.println(SingleObject3Hanger.getInstance().hashCode());
                // System.out.println(SingleObject4DCL.getInstance().hashCode());
                // System.out.println(SingleObject5Register.getInstance().hashCode());
                // System.out.println(SingleObject6Enum.INSTANCE.hashCode());
            }).start();
        }
        long endTime = System.currentTimeMillis();
        Thread.sleep(100L);
        System.out.println("耗时：" + (endTime - beginTime) + "ms");
    }
}
