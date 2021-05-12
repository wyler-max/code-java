package org.example.practicejava.deepcopy.genericity;

/**
 * 泛型类
 *
 * T 可以随便写为任意标识，常见的如 T、E、K、V等形式的参数常用于表示泛型
 */
public class Generic<T> {

    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
