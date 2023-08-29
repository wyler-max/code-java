package org.example.practice.practicecode.test.worklab;

import org.springframework.beans.BeanUtils;

/**
 * @author wangyulin
 * @date 2021/6/3
 */
public class Temp2 {
    public static void main(String[] args) {
        ObjC c = new ObjC();
        c.setIntVal((byte)10);
        c.setStringVal("objC");
        c.setStringVal2("objC-2");
        System.out.println(c.toString());
        ObjD d = new ObjD();
        /**
         * 基本数据类型和其包装类可以互相拷贝，隐式转换不能拷贝，不同数据类型不能拷贝
         */
        BeanUtils.copyProperties(c, d);
        System.out.println(d.toString());

        ObjE objE = new ObjE();
        System.out.println(objE.getIntVal());
    }
}
