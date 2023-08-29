package org.example.practice.practicecode.skill.designpattern.actionType.chain;

/**
 * 责任链模式，为请求创建了一个接收者对象的链。
 */
public class Test {
    public static void main(String[] args) {
        Client client = new Client();
        // 不同职级的领导
        Approver groupLeader = new GroupApprove("Tom");
        Approver departmentLeader = new DepartmentApprove("Jerry");
        Approver vicePresident = new VicePresidentApprove("Kate");
        Approver president = new PresidentApprove("Trump");

        // 设置了一个审批链路 groupLeader -> departmentLeader -> vicePresident -> president
        groupLeader.setSuccessor(departmentLeader);
        departmentLeader.setSuccessor(vicePresident);
        vicePresident.setSuccessor(president);
        // 发起审批
        groupLeader.processRequest(client.sendRequest(1, 1000, 40));
    }
}
