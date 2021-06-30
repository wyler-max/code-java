package org.example.practicecode.designpattern.actionType.command;

/**
 * 命令模式，是一种数据驱动的设计模式。
 *
 * 将对一个或多个对象的请求、操作封装成一个对象（遥控器）</br>
 * 1、可以实现对请求、操作行为的二次封装</br>
 * 2、可以将操作独立出来，形成一个遥控器。</br>
 *
 * 调用者 -> 命令 -> 目标对象 ====> 调用者 -> wapper(prefix-do, 命令, suffix-do) -> 目标对象
 */
public class Test {

    public static void main(String[] args) {
        // 遥控器
        Control control = new Control();
        // 控制灯
        Light light1 = new Light("客厅");
        Light light2 = new Light("卧室");
        Light light3 = new Light("厨房");
        LightControl lightControl = new LightControl(light2);
        System.out.println("控制灯");
        control.commandTurnOn(lightControl);
        control.commandTurnOff(lightControl);

        // 控制电视
        TV tv1 = new TV("小米");
        TV tv2 = new TV("海尔");
        TVControl tvControl = new TVControl(tv1);
        System.out.println("电视机");
        control.commandTurnOn(tvControl);
        control.commandTurnOff(tvControl);
    }
}
