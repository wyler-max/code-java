package org.example.practicescaffold.common.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import lombok.Data;

/**
 * 用户token类
 */
@Data
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private int loginTime;

    public UserToken() {}

    public UserToken(long id) {
        this.id = id;
        // 时效1小时
        this.loginTime = (int)(DateUtils.truncate(new Date(), Calendar.HOUR).getTime() / 1000);
    }

    public UserToken(long id, int loginTime) {
        this.id = id;
        this.loginTime = loginTime;
    }
}
