package org.example.practice.practiceknowbox.common.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import lombok.Data;

/**
 * 用户token类
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Data
public class UserToken implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private int loginTime;

    private int length;

    public UserToken() {}

    public UserToken(String id) {
        this.id = id;
        this.loginTime = (int)(DateUtils.truncate(new Date(), Calendar.HOUR).getTime() / 1000);// 一小时之内一样
        this.length = id.length();
    }

    public UserToken(String id, int loginTime) {
        this.id = id;
        this.loginTime = loginTime;
        this.length = id.length();
    }
}
