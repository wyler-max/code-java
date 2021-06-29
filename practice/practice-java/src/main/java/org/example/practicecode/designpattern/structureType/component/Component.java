package org.example.practicecode.designpattern.structureType.component;

/**
 * 组件
 */
public interface Component {
    void add(Component component);

    void remove(Component component);

    Component getChild(int i);

    void operation();
}
