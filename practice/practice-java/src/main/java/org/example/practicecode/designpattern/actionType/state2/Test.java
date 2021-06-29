package org.example.practicecode.designpattern.actionType.state2;

/**
 *
 */
public class Test {
    public static void main(String[] args) {
        Player player = new Player();
        HitState hitState = new HitState();
        hitState.setState(player);

        JumpState jumpState = new JumpState();
        jumpState.setState(player);
    }
}
