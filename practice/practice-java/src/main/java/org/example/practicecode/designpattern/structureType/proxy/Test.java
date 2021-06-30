package org.example.practicecode.designpattern.structureType.proxy;

/**
 * 代理模式，使用一个类代表另一个类的功能。</br>
 * 1、静态代理 </br>
 * 2、动态代理 a. JDK Proxy 基于反射 b. Cglib 基于asm框架
 */
public class Test {
    public static void main(String[] args) {

        BuyHouse buyHouse = new BuyHouseImpl();

        // 静态代理
        /*System.out.println("静态代理：========");
        BuyHouseProxy buyHouseProxy = new BuyHouseProxy();
        buyHouseProxy.buyHouseProxy(buyHouse);
        buyHouseProxy.buyHouse();

        // 动态代理 jdk proxy
        System.out.println("动态代理：========");
        BuyHouse buyHouseProxy2 = (BuyHouse)Proxy.newProxyInstance(buyHouse.getClass().getClassLoader(),
            buyHouse.getClass().getInterfaces(), new BuyHouseProxy21(buyHouse));
        buyHouseProxy2.buyHouse();*/

        // 动态代理 cglib
        System.out.println("cglib代理：========");
        BuyHouseProxy31 cglibProxy = new BuyHouseProxy31();
        BuyHouse proxyInstance = (BuyHouse)cglibProxy.getInstance(buyHouse);
        proxyInstance.buyHouse();
    }
}
