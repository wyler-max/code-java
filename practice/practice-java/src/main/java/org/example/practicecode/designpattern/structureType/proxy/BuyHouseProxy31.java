package org.example.practicecode.designpattern.structureType.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Cglib 代理 1
 *
 * Enhancer类是CGLib中的一个字节码增强器，它可以方便的对想要处理的类进行扩展
 */
public class BuyHouseProxy31 implements MethodInterceptor {

    private Object object;

    // 获取 cglib 生成的实例
    public Object getInstance(final Object object) {
        // 设置被代理对象
        this.object = object;
        // 使用 Enhancer 生成一个代理类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    // 设置方法拦截器
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("买房前，准备钱");
        Object result = proxy.invoke(object, args);
        System.out.println("买房前，搞装修");
        return result;
    }
}
