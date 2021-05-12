package org.example.practicejava.deepcopy.serializable;

import lombok.Data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 用户实体类
 */
@Data
public class User1 implements Externalizable {

    // 序列化ID
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;

    // @Data注解实现 getter和setter方法 toString方法

    public User1(){}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }
}
