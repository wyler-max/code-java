package org.example.practicejava.deepcopy.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型demo
 *
 * 类型：泛型类、泛型接口、泛型方法
 */
public class Demo {

    public static void main(String[] args) {
        testGenericity();
    }

    public static void testGenericity() {
        List arrayList = new ArrayList();
        arrayList.add("aaaa");
        arrayList.add(100);

        for (int i = 0; i < arrayList.size(); i++) {
            String value = (String) arrayList.get(i);
            System.out.println("泛型测试：value="+value);
        }
    }

    public static void testGenericity2() {
        List<String> arrayList = new ArrayList();
        arrayList.add("aaaa");
        // 编译阶段报错
        //arrayList.add(100);

        for (int i = 0; i < arrayList.size(); i++) {
            String value = (String) arrayList.get(i);
            System.out.println("泛型测试：value="+value);
        }
    }

    /**
     * 在编译之后程序会采取去泛型化的措施，Java中的泛型，只在编译阶段有效
     *
     * 在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。
     * 也就是说，泛型信息不会进入到运行时阶段
     */
    public static void testGenericity3() {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        Class classStringList = stringList.getClass();
        Class classIntegerList = integerList.getClass();

        if (classStringList.equals(classIntegerList)) {
            System.out.println(true);
        }
    }

}
