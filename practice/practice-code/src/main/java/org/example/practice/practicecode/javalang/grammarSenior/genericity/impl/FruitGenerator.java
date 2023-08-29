package org.example.practice.practicecode.javalang.grammarSenior.genericity.impl;

import org.example.practice.practicecode.javalang.grammarSenior.genericity.Generator;

/**
 * 泛型接口实现类
 *
 * 未传入泛型实参时，在声明类的时候，需将泛型的声明也一起加到类中，否则会报错
 */
public class FruitGenerator<T> implements Generator<T> {
    @Override
    public T next() {
        return null;
    }
}
