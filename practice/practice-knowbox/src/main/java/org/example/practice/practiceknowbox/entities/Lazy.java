package org.example.practice.practiceknowbox.entities;

import java.util.function.Supplier;

/**
 * 为了方便与标准的 Java 函数式接口交互，Lazy 也实现了 Supplier
 */
public class Lazy<T> implements Supplier<T> {

    private final Supplier<? extends T> supplier;

    /**
     * 利用 value 属性缓存 supplier 计算后的值
     */
    private T value;

    public Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    public static boolean isNull(Supplier supplier) {
        return supplier == null;
    }

    /**
     *
     */
    @Override
    public T get() {
        if (value == null) {
            if (supplier == null) {
                return null;
            }
            T newValue = supplier.get();
            if (newValue == null) {
                throw new IllegalStateException("Lazy value can not be null");
            }
            value = newValue;
        }
        return value;
    }
}
