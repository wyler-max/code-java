package org.example.practice.practiceknowbox.common.aliyun.mq;

import java.io.Serializable;

import lombok.Data;

/**
 * @author yijiu.chen
 * @date 2020/04/25
 */
@Data
public class MessageEventObject implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private int type;

    private Object message;

    public static MessageEventObject of(int type, Object message) {
        MessageEventObject object = new MessageEventObject();
        object.type = type;
        object.message = message;

        return object;
    }

}
