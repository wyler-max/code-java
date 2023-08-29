package org.example.practice.practicecode.javalang.objectcopy.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import lombok.Data;

/**
 * 用户实体类
 */
@Data
public class UserExternalizable implements Externalizable {

    // 序列化ID
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;

    public UserExternalizable() {}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {}

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {}
}
