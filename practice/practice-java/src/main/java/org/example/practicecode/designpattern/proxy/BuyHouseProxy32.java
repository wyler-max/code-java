package org.example.practicecode.designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Cglib 代理 2
 */
public class BuyHouseProxy32 {

    public static void main(String[] args) {
        final BuyHouseImpl buyHouseImpl = new BuyHouseImpl();

        BuyHouseImpl buyHouseProxy = (BuyHouseImpl)Enhancer.create(buyHouseImpl.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("买房前，准备钱");
                Object result = proxy.invoke(buyHouseImpl, args);
                System.out.println("买房前，搞装修");
                return result;
            }
        });

        System.out.println("cglib代理 2：========");
        buyHouseProxy.buyHouse();
    }
}
