package org.example.practicecode.designpattern.chain;

import com.example.designpattern.chain.PurchaseRequest;

/**
 * 决策者抽象类
 */
public abstract class Approver {
    Approver successor;
    String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    public abstract void processRequest(PurchaseRequest request);

}
