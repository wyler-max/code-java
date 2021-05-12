package org.example.practicecode.designpattern.mediator;

import com.example.designpattern.mediator.ChatRoom;

/**
 * 用户类
 */
public class User {
    private String name;

    public User(String name){
        this.name  = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(String message){
        ChatRoom.showMessage(this, message);
    }
}
