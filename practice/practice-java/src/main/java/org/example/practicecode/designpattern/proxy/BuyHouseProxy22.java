package org.example.practicecode.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理 2
 */
public class BuyHouseProxy22{

    public static void main(String[] args) {
        BuyHouseImpl buyHouseImpl = new BuyHouseImpl();

        BuyHouse buyHouseProxy = (BuyHouse)Proxy.newProxyInstance(buyHouseImpl.getClass().getClassLoader(), buyHouseImpl.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("买房前，准备钱");
                Object result = method.invoke(buyHouseImpl, args);
                System.out.println("买房前，搞装修");
                return result;
            }
        });

        System.out.println("动态代理 2：========");
        buyHouseProxy.buyHouse();
    }
}
