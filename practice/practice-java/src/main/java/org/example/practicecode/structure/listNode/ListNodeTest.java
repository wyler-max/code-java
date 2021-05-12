package org.example.practicecode.structure.listNode;


import org.junit.Test;

public class ListNodeTest {

    @Test
    public void test() {
        ListNode node3 = new ListNode(3, null);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        System.out.println("普通迭代");
        ListNodeImpl.traverse(node1);
        System.out.println("递归迭代");
        ListNodeImpl.traverseRecursion(node1);
    }

}
