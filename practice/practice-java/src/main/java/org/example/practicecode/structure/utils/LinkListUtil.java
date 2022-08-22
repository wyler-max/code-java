package org.example.practicecode.structure.utils;

import org.example.practicecode.structure.linkList.ListNode;

/**
 * 链表工具
 */
public class LinkListUtil {

    /**
     * 构建链表
     */
    public static ListNode buildLinkList(int nodeCount) {
        ListNode head = null;
        for (int count = nodeCount; count > 0; count--) {
            head = new ListNode(count, head);
        }
        return head;
    }

    public static ListNode buildLinkList(int[] valArray) {
        ListNode head = null;
        for (int i = valArray.length - 1; i >= 0; i--) {
            head = new ListNode(valArray[i], head);
        }
        return head;
    }

    /**
     * 迭代访问 ListNode
     */
    public static void traverse(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next == null ? "\n" : " -> "));
            head = head.next;
        }
    }

    /**
     * 递归访问 ListNode
     */
    public static void traverseRecursion(ListNode head) {
        if (null != head) {
            System.out.print(head.val + (head.next == null ? "\n" : " -> "));
            traverseRecursion(head.next);
        }
    }
}
