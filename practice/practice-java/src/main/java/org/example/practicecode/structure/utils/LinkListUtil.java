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

    /**
     * 迭代访问 ListNode
     */
    public static void traverse(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * 递归访问 ListNode
     */
    public static void traverseRecursion(ListNode head) {
        if (null != head) {
            System.out.println(head.val);
            traverseRecursion(head.next);
        }
    }
}
