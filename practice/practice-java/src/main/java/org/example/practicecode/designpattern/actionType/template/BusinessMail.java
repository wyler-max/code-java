package org.example.practicecode.designpattern.actionType.template;

import org.apache.commons.lang3.StringUtils;

/**
 * 商务邮件
 */
public class BusinessMail extends Mail {
    @Override
    public void setMailTitle() {
        this.mailTitle = "【商务】" + StringUtils.join(this.mailTitle);
        System.out.println("商务邮件标题" + this.mailTitle);
    }

    @Override
    public void setMailReciver() {
        System.out.println("商务邮件往来收件人");
    }

    @Override
    public void setMailContent() {
        System.out.println("商务邮件内容");
    }

    @Override
    public void setMailFormat() {
        System.out.println("商务邮件格式");
    }
}
