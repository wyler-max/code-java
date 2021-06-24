package org.example.practicejava.deepcopy.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 序列化测试类
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        serializeUser();
        // Thread.sleep(5000);
        deSerializeUser();

        HashMap<String, String> map = new HashMap<>();
        map.put(null, "1");
        System.out.println(map);
        System.out.println(map.get(null));
        @SuppressWarnings({"rawtypes", "unchecked"})
        Node<String, String>[] table = (Node<String, String>[])new Node[16];
        System.out.println(table[0]);
    }

    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }

    private static String getFilePath() throws IOException {
        File file = new File("");
        return file.getCanonicalPath() + "/practice-java/src/main/resources/uploads/template";
    }

    // 序列化方法
    private static void serializeUser() throws IOException {
        String fileName = getFilePath();
        User user = new User();
        user.setName("Allen");
        user.setAge(18);
        user.setDesc("测试测试");

        // 序列化对象到文件中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(user);
        oos.close();
        System.out.println("序列化对象成功，并存储到文件");
    }

    // 反序列化方法
    private static void deSerializeUser() throws IOException, ClassNotFoundException {
        String fileName = getFilePath();
        File file = new File(fileName);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User newUser = (User)ois.readObject();
        System.out.println("反序列化成功 " + newUser.toString());
    }
}
