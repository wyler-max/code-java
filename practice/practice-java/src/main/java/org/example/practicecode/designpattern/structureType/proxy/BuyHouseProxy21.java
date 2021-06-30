package org.example.practicecode.designpattern.structureType.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理 1 使用 java.lang.reflect.InvocationHandler
 */
public class BuyHouseProxy21 implements InvocationHandler {

    private Object object;

    public BuyHouseProxy21(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前，准备钱");
        Object result = method.invoke(object, args);
        System.out.println("买房后，搞装修");
        return result;
    }
}
