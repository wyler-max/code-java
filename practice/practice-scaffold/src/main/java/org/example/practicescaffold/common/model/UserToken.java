package org.example.practicescaffold.common.model;

import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
