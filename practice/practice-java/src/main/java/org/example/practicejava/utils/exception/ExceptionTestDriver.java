package org.example.practicejava.utils.exception;

import org.junit.Test;

public class ExceptionTestDriver {

    /**
     * try(a.func(); b.func()) 可捕获多个异常
     */
    @Test
    public void test1() {
        try (FileOptionA fileOptionA = new FileOptionA(); FileOptionB fileOptionB = new FileOptionB()) {
            fileOptionA.open();
            // fileOptionA.read();
            fileOptionB.open();
            // fileOptionB.read();
        } catch (Exception e) {
            System.out.println("main excetion");
            System.out.println(e.getMessage());
            System.out.println("count=" + e.getSuppressed().length);
            // 多个异常被压到栈中，遍历可得
            for (Throwable throwable : e.getSuppressed()) {
                System.out.println(throwable.getMessage());
            }
        }
    }

    /**
     * 测试捕获并重新抛出异常
     */
    @Test
    public void test2() throws Exception {
        try {
            System.out.println("test2 try.");
            test21();
        } catch (NullPointerException ex) {
            System.out.println("test2 catch exception.");
            // 方法1 其中1和2是两种不同的构造函数的实现，建议用2，简洁
            /*Exception bussinessEx = new Exception("package exception");
            bussinessEx.initCause(ex);
            throw bussinessEx;*/
            // 方法2 重新抛出异常
            throw new Exception("package exception", ex);
            // 方法3 失败，Self-causation not permitted
            // throw (Exception)ex.fillInStackTrace().initCause(ex);
        }
    }

    private void test21() {
        System.out.println("test21 run.");
        test22();
    }

    private void test22() {
        System.out.println("test22 run.");
        throw new NullPointerException("test22 str is null");
    }
}
