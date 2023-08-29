package org.example.practice.practicecode.skill.designpattern.createType.builderKFC;

/**
 * 瓶装类，实现包装接口
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
