package org.example.practice.practicecode.skill.designpattern.actionType.template;

/**
 * 邮件模板抽象类
 */
public abstract class Mail {

    // 信件属性
    protected String mailTitle;
    protected String mailReceiver;
    protected String mailContext;
    protected String mailFormat;

    // 定义模板
    public void sendMail() {
        this.setMailTitle();
        this.setMailReciver();
        this.setMailContent();
        this.setMailFormat();
    }

    // 模板中的方法
    public abstract void setMailTitle();

    public abstract void setMailReciver();

    public abstract void setMailContent();

    public abstract void setMailFormat();
}
