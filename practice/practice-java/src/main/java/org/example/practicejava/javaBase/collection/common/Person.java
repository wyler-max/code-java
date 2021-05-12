package org.example.practicejava.javaBase.collection.common;

import lombok.Data;

@Data
public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 当compareTo方法返回0的时候集合中只有一个元素
     * 当compareTo方法返回正数的时候集合会怎么存就怎么取
     * 当compareTo方法返回负数的时候集合会倒序存储
     * @param o
     * @return
     */
    @Override
    public int compareTo(Person o) {
        return 1;
    }
}
