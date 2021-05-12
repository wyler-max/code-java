package org.example.practicecode.designpattern.state2;

import com.example.designpattern.state2.StateEnum;

/**
 *
 */
public class HitState implements State {

    @Override
    public void setState(Player player) {
        player.setState(StateEnum.HIT.getType());
        System.out.println("降龙十八掌");
    }
}
