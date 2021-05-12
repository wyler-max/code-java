package org.example.practicejava.deepcopy.test.deepserializable;

import com.example.javalab.deepcopy.clone.deepserializable.Address;
import com.example.javalab.deepcopy.clone.deepserializable.Person;
import org.junit.Test;

public class MTest {

    /**
     * 深拷贝测试
     *
     * 深拷贝-序列化方式#
     * 这种方式其实就是将对象转成二进制流，然后再把二进制流反序列成一个java对象，这时候反序列化生成的对象是一个全新的对象，
     * 里面的信息与原对象一样，但是所有内容都是一份新的。
     *
     * 这种方式需要注意的地方主要是所有类都需要实现Serializable接口，以便进行序列化操作。
     *
     */
    @Test
    public void deepCloneSerializableTest() throws Exception {
        Person person = new Person(15, "zhangsan", new Address("四川", "天府二街"));

        Person clonePerson = (Person) person.deepClone();

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
