package org.example.practicejava.utils.exception;

/**
 * @author wangyulin
 * @date 2020/5/27
 */
public class ExceptionCause {

    public static void main(String[] args) throws Exception {
        test1();
    }

    /**
     * 测试捕获并重新抛出异常
     */
    private static void test1() throws Exception {
        try {
            System.out.println("test1 try.");
            test2();
        } catch (NullPointerException ex) {
            System.out.println("test1 catch exception.");
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

    private static void test2() {
        System.out.println("test2 run.");
        test3();
    }

    private static void test3() {
        System.out.println("test3 run.");
        throw new NullPointerException("test3 str is null");
    }
}
