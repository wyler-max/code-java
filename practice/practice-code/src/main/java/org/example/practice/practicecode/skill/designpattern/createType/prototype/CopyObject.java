package org.example.practice.practicecode.skill.designpattern.createType.prototype;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 测试原型模式拷贝深度
 */
@Getter
@Setter
public class CopyObject implements Cloneable {
    private String name;
    private List<Integer> list;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "CopyObject{" + "CopyObject='" + this.hashCode() + '\'' + "name='" + name + '\'' + ", list=" + list
            + ", list=" + list.hashCode() + '}';
    }
}
