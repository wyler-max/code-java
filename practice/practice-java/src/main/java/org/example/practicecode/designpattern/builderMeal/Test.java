package org.example.practicecode.designpattern.builderMeal;

/**
 * 测试类
 */
public class Test {
    public static void main(String[] args) {
        ConcreteMealBuilder builder = new ConcreteMealBuilder();
        builder.prepareMainItem("鸡腿").prepareToy("恐龙🦕");
    }
}
