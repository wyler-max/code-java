package org.example.springstart.aop.proxy.proxy;

/**
 * 一个生产者
 */
public class Producer implements IProducer{

    /**
     * 销售
     * @param money
     */
    @Override
    public void saleProduct(float money){
        System.out.println("销售产品，并拿到钱："+money);
    }

    /**
     * 售后
     * @param money
     */
    @Override
    public void afterService(float money){
        System.out.println("提供售后服务，并拿到钱："+money);
    }
}
