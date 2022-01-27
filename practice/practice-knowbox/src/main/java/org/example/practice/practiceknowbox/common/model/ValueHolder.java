package org.example.practice.practiceknowbox.common.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author yijiu.chen
 * @date 2020/05/22
 */
@Data
public class ValueHolder<T> implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    private T value;

}
