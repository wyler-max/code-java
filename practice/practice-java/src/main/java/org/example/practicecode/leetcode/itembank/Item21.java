package org.example.practicecode.leetcode.itembank;

import org.example.practicecode.structure.linkList.ListNode;
import org.junit.Test;

/**
 * 合并两个升序链表
 *
 * 思路：
 * 1、双指针法
 * 2、递归合并法
 *
 * 示例：
 * 输入：1->2->4 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Item21 {

    @Test
    public void demo() {
        // 1-2-4
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        // 1-3-4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        // 合并有序链表
        ListNode res = mergeListRecursion(l1, l2);
        // 输出合并后的链表
        ListNode.print(res);
    }

    /**
     * 双指针，比较合并
     */
    public ListNode mergeLists(ListNode l1, ListNode l2) {
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

    /**
     * 递归合并
     */
    public ListNode mergeListRecursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeListRecursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeListRecursion(l1, l2.next);
            return l2;
        }
    }

}
