package org.example.practicejava.deepcopy.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
public class User implements Serializable {

    // 序列化ID
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;

    // @Data注解实现 getter和setter方法 toString方法
}
