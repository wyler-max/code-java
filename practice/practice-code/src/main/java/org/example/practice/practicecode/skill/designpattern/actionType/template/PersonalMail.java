package org.example.practice.practicecode.skill.designpattern.actionType.template;

import org.apache.commons.lang3.StringUtils;

/**
 * 私人邮件
 */
public class PersonalMail extends Mail {
    @Override
    public void setMailTitle() {
        this.mailTitle = "【私人】" + StringUtils.join(this.mailTitle);
        System.out.println("私人邮件标题" + this.mailTitle);
    }

    @Override
    public void setMailReciver() {
        System.out.println("私人邮件往来收件人");
    }

    @Override
    public void setMailContent() {
        System.out.println("私人邮件内容");
    }

    @Override
    public void setMailFormat() {
        System.out.println("私人邮件格式");
    }
}
