package org.example.practice.practicecode.javalang.javaClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.example.practice.practicecode.javalang.common.User;

public class InvokeClassTest {

    public static void main(String[] args) {
        User user = new User();
        user.hobby = "dance";
        invokeField(user);
        invokeMethod(user);
    }

    public static void invokeField(User user) {
        Object obj = user;
        // 获取 class
        Class<?> aClass = obj.getClass();
        // 获取 class.fields
        Field[] fields = aClass.getDeclaredFields();
        System.out.println("遍历类属性");
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName());
            try {
                System.out.println(field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void invokeMethod(User user) {
        Object obj = user;
        // 获取 class
        Class<?> aClass = obj.getClass();
        // 获取 class.methods
        Method[] methods = aClass.getDeclaredMethods();
        System.out.println("遍历执行类方法");
        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println(method.getName());
            try {
                // 通过 method.invoke 反射执行方法
                System.out.println(method.invoke(obj, new String[] {}));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
