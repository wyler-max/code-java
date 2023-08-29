package org.example.practice.practicecode.javalang.java8feature;

import java.util.Optional;

import org.junit.Test;

/**
 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。 Optional
 * 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。 Optional 类的引入很好的解决空指针异常。
 */
public class OptionalTest {

    @Test
    public void testOptional() {
        Integer value1 = null;
        Integer value2 = new Integer(10);

        Optional<Integer> a = Optional.ofNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        System.out.println(sum(a, b));;
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        Integer val1 = a.orElse(2);
        Integer val2 = b.get();
        return val1 + val2;
    }
}
