package org.example.practicecode.designpattern.builder;

import com.example.designpattern.builder.Packing;
import com.example.designpattern.builder.Wrapper;

/**
 * 汉堡抽象类，实现商品
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
