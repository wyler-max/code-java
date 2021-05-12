package org.example.practicecode.designpattern.adapter;

import com.example.designpattern.adapter.Adapter;
import com.example.designpattern.adapter.AdapterObject;
import com.example.designpattern.adapter.ConCreteTarget;

/**
 * @author wangyulin
 * @date 2020/5/28
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("==== 直接实现Target ======");
        ConCreteTarget conCreteTarget = new ConCreteTarget();
        conCreteTarget.request();

        System.out.println("==== 类适配器 ======");
        Adapter adapter = new Adapter();
        adapter.request();

        System.out.println("==== 对象适配器 ======");
        AdapterObject adapterObject = new AdapterObject();
        adapterObject.request();
    }
}
