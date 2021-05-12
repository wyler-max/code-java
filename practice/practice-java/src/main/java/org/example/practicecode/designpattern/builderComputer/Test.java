package org.example.practicecode.designpattern.builderComputer;

import com.example.designpattern.builderComputer.DELLComputerBuilder;
import com.example.designpattern.builderComputer.Director;
import com.example.designpattern.builderComputer.HPComputerBuilder;

/**
 * 生成器模式测试
 */
public class Test {
    public static void main(String[] args) {
        Director director = new Director();

        HPComputerBuilder hpComputerBuilder = new HPComputerBuilder();
        director.setComputerBuilder(hpComputerBuilder);
        director.constructComputer();
        director.getComputer();

        System.out.println("==============");
        DELLComputerBuilder dellComputerBuilder = new DELLComputerBuilder();
        director.setComputerBuilder(dellComputerBuilder);
        director.constructComputer();
        director.getComputer();
    }
}
