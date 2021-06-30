package org.example.practicecode.designpattern.actionType.chain;

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

    // 审批处理，不同职级处理的审批权限不同 即 最大审批金额不同
    public abstract void processRequest(PurchaseRequest request);

}
