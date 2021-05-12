package org.example.practicecode.designpattern.adapterUSB;

import com.example.designpattern.adapterUSB.AdapterUSB2VGA1_Class;
import com.example.designpattern.adapterUSB.AdapterUSB2VGA2_Object;
import com.example.designpattern.adapterUSB.AdapterUSB2VGA3_Interface;

/**
 * 适配器测试类：usb 转 vga
 */
public class Test {
    public static void main(String[] args) {
        String data = "dlrow olleh";

        // 类适配器
        AdapterUSB2VGA1_Class adapterClass = new AdapterUSB2VGA1_Class();
        adapterClass.showScreenData(data);

        // 对象适配器
        AdapterUSB2VGA2_Object adapterObject = new AdapterUSB2VGA2_Object();
        adapterObject.showScreenData(data);

        // 接口适配器
        AdapterUSB2VGA3_Interface adapterInterface = new AdapterUSB2VGA3_Interface();
        adapterInterface.showScreenData(data);
    }
}
