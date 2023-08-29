package org.example.practice.practicecode.javalang.objectcopy.deepserializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 核心代码，序列化与反序列化对象
 */
public class DeepClone implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 利用序列化和反序列化进行对象的深拷贝
     *
     * @return
     * @throws Exception
     */
    public Object deepClone() throws Exception {
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }
}
