package org.example.practicejava.deepcopy.genericity.impl;

import org.example.practicejava.deepcopy.genericity.Generator;

import java.util.Random;

/**
 * 泛型接口实现类
 *
 * 传入泛型实参时，
 * 在声明类的时：      不需要需在类上声明泛型或实参类型
 * 在实现泛型接口时：   所有使用泛型的地方都要替换成传入的实参类型
 */
public class FruitGenerator2 implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Orange"};

    @Override
    public String next() {
        Random random = new Random();
        return fruits[random.nextInt(3)];
    }
}
