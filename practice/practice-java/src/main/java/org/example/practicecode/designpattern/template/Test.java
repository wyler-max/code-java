package org.example.practicecode.designpattern.template;

/**
 * 模板模式测试类
 */
public class Test {
    public static void main(String[] args) {

        System.out.println("发送商务邮件：======");
        BusinessMail businessMail = new BusinessMail();
        businessMail.sendMail();

        System.out.println("发送私人邮件：======");
        PersonalMail personalMail = new PersonalMail();
        personalMail.sendMail();
    }
}
