package org.example.practicejava.jvm;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangyulin
 * @date 2020/7/2
 */
public class JvmTest {

    public class A {

    }

    @Test
    public void testPrint() {
    }

    public static void print() {
        System.out.println("111");
    }

    @Test
    public void testJvm() {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
