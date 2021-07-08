package org.example.practicejava.objectcopy.deepClone;

/**
 * 在Clone中，手动处理泛型Address的拷贝
 */
public class Person implements Cloneable {
    private int age;
    private String name;
    // Address 实现 Cloneable 接口
    private Address address;

    public Person(int age, String name, Address address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person person = (Person)super.clone();
        // 处理 Address 的拷贝
        person.address = (Address)address.clone();
        return person;
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
