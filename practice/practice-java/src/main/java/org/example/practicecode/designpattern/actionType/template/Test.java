package org.example.practicecode.designpattern.actionType.template;

/**
 * 模板模式，使用一个抽象类公开定义了执行它的方法的方式/模板。子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。
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
