package org.example.practicecode.structure.linkList;

/**
 * 链表节点
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }

    public static void print(ListNode listNode) {
        if (listNode == null) {
            System.out.println("空链表");
            return;
        }
        while (listNode.next != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
        System.out.println(listNode.val);
    }
}
