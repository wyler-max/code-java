package org.example.practicecode.designpattern.createType.prototype;

import lombok.Data;

/**
 * 信件实体类
 */
@Data
public class Mail implements Cloneable {
    private String receiver;
    private String subject;
    private String content;
    private String tail;

    /**
     * 构造信件
     *
     * @param et
     */
    public Mail(EventTemplate et) {
        this.tail = et.getEventContent();
        this.subject = et.getEventSubject();
        try {
            // 模拟创建一个对象需要耗费比较长的时间
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写clone方法，实现对象拷贝
     *
     * @return
     */
    @Override
    public Mail clone() {
        Mail mail = null;
        try {
            mail = (Mail)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail;
    }
}
