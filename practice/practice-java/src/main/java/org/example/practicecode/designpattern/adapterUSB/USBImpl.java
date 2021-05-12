package org.example.practicecode.designpattern.adapterUSB;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;

/**
 * USB实现类
 */
public class USBImpl implements USB {

    @Override
    public String showData(String data) {
        System.out.println("USB输出数据: " + data);
        return data;
    }
}
