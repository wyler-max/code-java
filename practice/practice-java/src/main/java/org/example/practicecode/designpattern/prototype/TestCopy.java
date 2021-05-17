package org.example.practicecode.designpattern.prototype;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 测试拷贝深度
 */
public class TestCopy {

    public static void main(String[] args) {
        CopyObject obj1 = new CopyObject();
        obj1.setName(obj1.getClass().getSimpleName());
        List<Integer> obj1List = Lists.newArrayList(1, 2);
        obj1.setList(obj1List);
        System.out.println(obj1);

        try {
            CopyObject obj2 = (CopyObject)obj1.clone();
            System.out.println(obj2);
            obj2.getList().add(3);
            System.out.println(obj1);
            System.out.println(obj2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
