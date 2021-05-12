package org.example.practicecode.designpattern.builder;

import com.example.designpattern.builder.Packing;

/**
 * 瓶装类，实现包装接口
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
