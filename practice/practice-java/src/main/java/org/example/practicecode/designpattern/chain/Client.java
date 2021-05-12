package org.example.practicecode.designpattern.chain;

import com.example.designpattern.chain.PurchaseRequest;

/**
 * 发起者
 */
public class Client {
    public Client() {
    }

    public PurchaseRequest sendRequest(int type, int number, float price) {
        return new PurchaseRequest(type, number, price);
    }
}
