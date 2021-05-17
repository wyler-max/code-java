package org.example.practicecode.designpattern.chain;

/**
 * 组长
 */
public class GroupApprove extends Approver {

    public GroupApprove(String name) {
        super(name + " GroupLeader");
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getSum() < 5000) {
            System.out.println("This request " + request.getId() + " will be handled by " + this.name + ".");
        } else {
            successor.processRequest(request);
        }
    }
}
