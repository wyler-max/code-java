package org.example.practice.practiceknowbox.entities.function;

/**
 * 自定义函数式接口
 */
@FunctionalInterface
public interface CustomFunctional<T, R> {

    R doAcction(T t);
}
