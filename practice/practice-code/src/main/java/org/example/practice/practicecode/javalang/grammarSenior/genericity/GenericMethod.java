package org.example.practice.practicecode.javalang.grammarSenior.genericity;

/**
 * 泛型方法
 */
public class GenericMethod {

    /**
     *
     * @param tClass
     *            传入的泛型实参
     * @param <T>
     *            只有声明了<T>的方法才是泛型方法，此时才可以在方法中使用泛型类型T
     * @return 返回值为T类型
     */
    public <T> T genericMethod(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T instance = tClass.newInstance();
        return instance;
    }
}
