package org.example.practice.practicecode.skill.designpattern.structureType.proxy;

/**
 * 静态代理
 */
public class BuyHouseProxy implements BuyHouse {

    private BuyHouse buyHouse;

    public void buyHouseProxy(final BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHouse() {
        System.out.println("买房前，准备钱");
        buyHouse.buyHouse();
        System.out.println("买房后，搞装修");
    }

}
