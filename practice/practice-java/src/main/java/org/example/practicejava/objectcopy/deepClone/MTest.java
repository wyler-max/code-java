package org.example.practicejava.objectcopy.deepClone;

import org.junit.Test;

public class MTest {

    /**
     * 深拷贝测试
     *
     * 深拷贝：即将引用类型的属性内容也拷贝一份新的。
     *
     * 有两种方式： </br>
     * 第一种是给需要拷贝的 引用类型 也实现 Cloneable 接口并覆写clone方法； </br>
     * 第二种是利用序列化。
     *
     * 深拷贝-clone方式 </br>
     * # 对于以上演示代码，利用clone方式进行深拷贝无非就是将Address类也实现Cloneable，然后对Person的clone方法进行调整。 </br>
     * 适用于引用类型变量较少的时候
     */
    @Test
    public void deepCloneTest() {
        Person person = new Person(15, "zhangsan", new Address("四川", "天府二街"));

        Person clonePerson = null;
        try {
            clonePerson = (Person)person.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("ShallowTest");
        System.out.println("Raw Data");
        System.out.println(person);
        System.out.println(clonePerson);

        System.out.println("Modify Data");
        clonePerson.setName("wangwu");
        clonePerson.setAge(20);
        Address address = clonePerson.getAddress();
        address.setStreet("天府四街");
        System.out.println(clonePerson);
        System.out.println(person);
    }
}
