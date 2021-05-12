package org.example.practicecode.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 合并两个升序链表
 *
 * 示例：
 * 输入：1->2->4 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Item21 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    public void demo() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode res = mergeTwoLists(l1, l2);
        printListNode(res);
    }

    /**
     * 示例：
     *  * 输入：1->2->4 1->3->4
     *  * 输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode list = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                list.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                list.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            list = list.next;
        }
        list.next = (l1 == null ? l2 : l1);
        return head.next;
    }

    private void printListNode(ListNode listNode) {
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
