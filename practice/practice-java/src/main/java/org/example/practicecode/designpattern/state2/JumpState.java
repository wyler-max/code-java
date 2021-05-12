package org.example.practicecode.designpattern.state2;

import com.example.designpattern.state2.StateEnum;

/**
 *
 */
public class JumpState implements State {

    @Override
    public void setState(Player player) {
        player.setState(StateEnum.JUMP.getType());
        System.out.println("飞檐走壁");
    }
}
