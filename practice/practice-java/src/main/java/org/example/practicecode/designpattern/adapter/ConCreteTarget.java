package org.example.practicecode.designpattern.adapter;

/**
 * 目标接口实现类
 */
public class ConCreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConCreteTarget目标方法");
    }
}
