package org.example.practicecode.designpattern.template;

/**
 * 私人邮件
 */
public class PersonalMail extends Mail {
    @Override
    public void mailTitle() {
        System.out.println("私人邮件标题");
    }

    @Override
    public void mailReciver() {
        System.out.println("私人邮件往来收件人");
    }

    @Override
    public void mailContent() {
        System.out.println("私人邮件内容");
    }

    @Override
    public void mailFormat() {
        System.out.println("私人邮件格式");
    }
}
