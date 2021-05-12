package org.example.practicejava.deepcopy.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化测试类
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        serializeUser();
        Thread.sleep(5000);
        deSerializeUser();
    }

    // 序列化方法
    private static void serializeUser() throws IOException {

        String fileName = "/Users/wangyulin/work/code/github.com/demo-java/src/main/resources/uploads/template";

        User user = new User();
        user.setName("Allen");
        user.setAge(18);

        // 序列化对象到文件中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(user);
        oos.close();
        System.out.println("序列化对象成功，并存储到文件");
    }

    // 反序列化方法
    private static void deSerializeUser() throws IOException, ClassNotFoundException {

        String fileName = "/Users/wangyulin/work/code/github.com/demo-java/src/main/resources/uploads/template";

        File file = new File(fileName);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User newUser = (User) ois.readObject();
        System.out.println("反序列化成功 " + newUser.toString());
    }
}
