package org.example.practicecode.designpattern.structureType.proxy;

import java.lang.reflect.Proxy;

/**
 * 代理模式测试类
 */
public class Test {
    public static void main(String[] args) {

        BuyHouse buyHouse = new BuyHouseImpl();

        // 静态代理
        System.out.println("静态代理：========");
        BuyHouseProxy buyHouseProxy = new BuyHouseProxy();
        buyHouseProxy.buyHouseProxy(buyHouse);
        buyHouseProxy.buyHouse();

        // 动态代理
        System.out.println("动态代理：========");
        BuyHouse buyHouseProxy2 = (BuyHouse)Proxy.newProxyInstance(buyHouse.getClass().getClassLoader(),
            buyHouse.getClass().getInterfaces(), new BuyHouseProxy21(buyHouse));
        buyHouseProxy2.buyHouse();

        // cglib 代理
        System.out.println("cglib代理：========");
        BuyHouseProxy31 cglibProxy = new BuyHouseProxy31();
        BuyHouseImpl proxyInstance = (BuyHouseImpl)cglibProxy.getInstance(buyHouse);
        proxyInstance.buyHouse();
    }
}
