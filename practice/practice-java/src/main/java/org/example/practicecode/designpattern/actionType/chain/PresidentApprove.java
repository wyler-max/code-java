package org.example.practicecode.designpattern.actionType.chain;

/**
 * 总裁
 */
public class PresidentApprove extends Approver {

    public PresidentApprove(String name) {
        super(name + " President");
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (100000 <= request.getSum()) {
            System.out.println("This request " + request.getId() + " will be handled by " + this.name + ".");
        } else {
            successor.processRequest(request);
        }
    }
}
