package org.example.practicecode.structure.listNode;


public class ListNodeImpl {

    // 迭代访问ListNode
    public static void traverse(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            System.out.println(p.val);
        }
    }

    // 递归访问ListNode
    public static void traverseRecursion(ListNode head) {
        if (null != head) {
            System.out.println(head.val);
            traverseRecursion(head.next);
        }
    }
}
