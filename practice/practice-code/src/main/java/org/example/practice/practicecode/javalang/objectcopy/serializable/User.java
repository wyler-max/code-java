package org.example.practice.practicecode.javalang.objectcopy.serializable;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户实体类
 */
@Data
public class User implements Serializable {

    // 序列化ID
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;

    // transient所修饰的变量不能被序列化
    transient String desc;
}
