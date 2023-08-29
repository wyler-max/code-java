package org.example.practice.practicecode.skill.designpattern.actionType.mediator;

import java.util.Date;

/**
 * 中介类
 */
public class ChatRoom {
    public static void showMessage(User user, String message){
        System.out.println(new Date().toString()
                + " [" + user.getName() +"] : " + message);
    }
}
