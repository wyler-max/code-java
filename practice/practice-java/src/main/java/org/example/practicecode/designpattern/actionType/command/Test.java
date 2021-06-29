package org.example.practicecode.designpattern.actionType.command;

/**
 * 命令模式测试类
 */
public class Test {
    public static void main(String[] args) {

        Control control = new Control();

        // 控制灯
        Light light1 = new Light("客厅");
        Light light2 = new Light("卧室");
        Light light3 = new Light("厨房");
        LightControl lightControl = new LightControl(light2);
        control.commandTurnOn(lightControl);
        control.commandTurnOff(lightControl);

        // 控制电视
        TV tv1 = new TV("小米");
        TV tv2 = new TV("海尔");
        TVControl tvControl = new TVControl(tv1);
        control.commandTurnOn(tvControl);
        control.commandTurnOff(tvControl);
    }
}
