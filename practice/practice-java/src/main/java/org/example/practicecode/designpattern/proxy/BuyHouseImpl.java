package org.example.practicecode.designpattern.proxy;

/**
 * 服务类实现类
 */
public class BuyHouseImpl implements BuyHouse {
    @Override
    public final void buyHouse() {
        System.out.println("我要买房");
    }
}
