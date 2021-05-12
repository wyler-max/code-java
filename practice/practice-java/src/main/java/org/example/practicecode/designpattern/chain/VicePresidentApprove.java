package org.example.practicecode.designpattern.chain;

/**
 * 副总裁
 */
public class VicePresidentApprove extends Approver {

    public VicePresidentApprove(String name) {
        super(name + " VicePresident");
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (10000 <= request.getSum() && request.getSum() < 100000) {
            System.out.println("This request " + request.getId() + " will be handled by " + this.name + ".");
        } else {
            successor.processRequest(request);
        }
    }
}
