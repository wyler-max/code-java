package org.example.practice.practicecode.skill.designpattern.createType.builderKFC;

/**
 * 纸质包装类，实现包装接口
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
