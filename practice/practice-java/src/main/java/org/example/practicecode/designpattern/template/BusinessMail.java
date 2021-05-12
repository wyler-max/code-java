package org.example.practicecode.designpattern.template;

/**
 * 商务邮件
 */
public class BusinessMail extends Mail {
    @Override
    public void mailTitle() {
        System.out.println("商务邮件标题");
    }

    @Override
    public void mailReciver() {
        System.out.println("商务邮件往来收件人");
    }

    @Override
    public void mailContent() {
        System.out.println("商务邮件内容");
    }

    @Override
    public void mailFormat() {
        System.out.println("商务邮件格式");
    }
}
