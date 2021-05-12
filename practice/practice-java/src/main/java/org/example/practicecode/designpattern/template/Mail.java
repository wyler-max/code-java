package org.example.practicecode.designpattern.template;

/**
 * 邮件模板抽象类
 */
public abstract class Mail {

    public void sendMail() {
        this.mailTitle();
        this.mailReciver();
        this.mailContent();
        this.mailFormat();
    }

    public abstract void mailTitle();
    public abstract void mailReciver();
    public abstract void mailContent();
    // 邮件格式
    public abstract void mailFormat();
}
