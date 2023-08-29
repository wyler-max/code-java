package org.example.practice.practicecode.skill.designpattern.actionType.command;

/**
 * 具体命令角色，灯的开关
 */
public class LightControl implements Command {

    private Light light;

    public LightControl(Light light) {
        this.light = light;
    }

    @Override
    public void turnOn() {
        light.on();
    }

    @Override
    public void turnOff() {
        light.off();
    }
}
