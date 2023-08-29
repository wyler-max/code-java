package org.example.practice.practicecode.skill.designpattern.createType.prototype;

import java.util.Random;

/**
 * 原型模式测试类
 */
public class Test {
    public static void main(String[] args) {

        // testMail();
        testMailClone();
    }

    private static void testMail() {
        int i = 0;
        int count = 10;
        EventTemplate et = new EventTemplate("9月份信用卡账单", "国庆抽奖活动...");
        long start = System.currentTimeMillis();
        while (i < count) {
            // 以下是每封邮件不同的地方
            Mail mail = new Mail(et);
            mail.setContent(getRandString(5) + ",先生（女士）:你的信用卡账单..." + mail.getTail());
            mail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
            // 然后发送邮件
            System.out.println(mail);
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
    }

    private static void testMailClone() {
        int i = 0;
        int count = 10;
        EventTemplate et = new EventTemplate("9月份信用卡账单", "国庆抽奖活动...");
        long start = System.currentTimeMillis();
        Mail mail = new Mail(et);
        while (i < count) {
            // 以下是每封邮件不同的地方
            Mail mailClone = mail.clone();
            mailClone.setContent(getRandString(5) + ",先生（女士）:你的信用卡账单..." + mailClone.getTail());
            mailClone.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
            // 然后发送邮件
            System.out.println(mailClone);
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
    }

    private static String getRandString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
