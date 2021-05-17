package org.example.practicecode.designpattern.chain;

/**
 * 责任链模式测试类
 */
public class Test {
    public static void main(String[] args) {
        Client client = new Client();
        Approver groupLeader = new GroupApprove("Tom");
        Approver departmentLeader = new DepartmentApprove("Jerry");
        Approver vicePresident = new VicePresidentApprove("Kate");
        Approver president = new PresidentApprove("Trump");

        groupLeader.setSuccessor(vicePresident);
        departmentLeader.setSuccessor(vicePresident);
        vicePresident.setSuccessor(president);
        president.setSuccessor(groupLeader);

        groupLeader.processRequest(client.sendRequest(1, 10000, 40));
    }
}
