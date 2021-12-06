package org.example.practicecode.designpattern.structureType.adapterUSB;

/**
 * VGA 接口，适配目标
 */
public interface VGA {
    String showData(String data);

    default void otherA() {
        // todo-nothing
    }

    default void otherB() {
        // todo-nothing
    }
}
