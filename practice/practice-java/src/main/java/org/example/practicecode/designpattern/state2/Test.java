package org.example.practicecode.designpattern.state2;

import com.example.designpattern.state2.HitState;
import com.example.designpattern.state2.JumpState;
import com.example.designpattern.state2.Player;

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
