package org.example.practice.practicecode.skill.designpattern.actionType.state2;

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
