package org.example.practice.practicecode.javalang.objectcopy.deepserializable;

/**
 * 利用序列化，进行深拷贝
 *
 * 实现Serializable接口，让对象支持序列化
 */
public class Person extends DeepClone {
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;
    private Address address;

    public Person(int age, String name, Address address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" + "age=" + age + ", name='" + name + '\'' + ", address=" + address + '}';
    }
}
