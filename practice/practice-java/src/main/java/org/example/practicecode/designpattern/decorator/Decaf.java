package org.example.practicecode.designpattern.decorator;

import com.example.designpattern.decorator.Coffee;

/**
 * Coffee 实现类
 */
public class Decaf extends Coffee {
    public Decaf() {
        super.setDescription("Decaf");
        super.setPrice(3.0F);
    }
}
