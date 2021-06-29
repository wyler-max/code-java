package org.example.practicecode.designpattern.actionType.chain;

/**
 * 部长
 */
public class DepartmentApprove extends Approver {

    public DepartmentApprove(String name) {
        super(name + " DepartmentLeader");
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (5000 <= request.getSum() && request.getSum() < 10000) {
            System.out.println("This request " + request.getId() + " will be handled by " + this.name + ".");
        } else {
            successor.processRequest(request);
        }
    }
}
