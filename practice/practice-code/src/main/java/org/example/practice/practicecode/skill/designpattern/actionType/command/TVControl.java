package org.example.practice.practicecode.skill.designpattern.actionType.command;

/**
 * 具体命令角色，电视遥控器
 */
public class TVControl implements Command {

    private TV tv;

    public TVControl(TV tv) {
        this.tv = tv;
    }

    @Override
    public void turnOn() {
        tv.on();
    }

    @Override
    public void turnOff() {
        tv.off();
    }
}
