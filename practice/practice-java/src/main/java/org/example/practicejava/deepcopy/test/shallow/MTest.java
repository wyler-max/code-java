package org.example.practicejava.deepcopy.test.shallow;

import com.example.javalab.deepcopy.clone.shallow.Address;
import com.example.javalab.deepcopy.clone.shallow.Person;
import org.junit.Test;

public class MTest {

    /**
     * 浅拷贝测试
     *
     * 浅拷贝：创建一个新对象，然后将当前对象的非静态字段复制到该对象，如果字段类型是值类型（基本类型）的，那么对该字段进行复制；
     * 如果字段是引用类型的，则只复制该字段的引用而不复制引用指向的对象。
     * 此时新对象里面的引用类型字段相当于是原始对象里面引用类型字段的一个副本，原始对象与新对象里面的引用字段指向的是同一个对象。
     *
     * 因此，修改clonePerson里面的address内容时，原person里面的address内容会跟着改变。
     */
    @Test
    public void shallowCloneTest() {
        Person person = new Person(15, "zhangsan", new Address("四川", "天府二街"));

        Person clonePerson = null;
        try {
            clonePerson = (Person) person.clone();
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
