package org.example.practicecode.designpattern.actionType.chain;

/**
 * 采购请求实体类
 */
public class PurchaseRequest {
    private int type = 0;
    private int number = 0;
    private float price = 0;
    private int id = 0;

    public PurchaseRequest(int type, int number, float price) {
        this.type = type;
        this.number = number;
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public float getSum() {
        return number * price;
    }

    public int getId() {
        return (int)(Math.random() * 1000);
    }
}
