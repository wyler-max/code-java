package org.example.practice.practiceknowbox.common.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author yijiu.chen
 * @date 2020/05/22
 */
@Data
public class ListHolder<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<T> list;

}
