package org.example.practice.practicecode.skill.designpattern.actionType.chain;

/**
 * 发起者
 */
public class Client {
    public Client() {}

    public PurchaseRequest sendRequest(int type, int number, float price) {
        return new PurchaseRequest(type, number, price);
    }
}
