package org.example.practicecode.leetcode.itembank;

import org.example.practicecode.structure.linkList.ListNode;
import org.junit.Test;

/**
 *
 */
public class TestItem {

    @Test
    public void testItem() {
        // listA: 1-2-7-8; listB 3-4-5-7-8
        ListNode n8 = new ListNode(8);
        ListNode n7 = new ListNode(7, n8);

        ListNode n2 = new ListNode(2, n7);
        ListNode n1 = new ListNode(1, n2);

        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);

        ListNode listA = n3;
        ListNode.print(listA);
        //ListNode.print(reverse(listA));
        //ListNode.print(reverseRecursion(listA));
        //ListNode.print(reverseN(listA, 3));
        //ListNode.print(reverseBetween(listA, 3, 4));
        //ListNode.print(reverse(listA, n5));
        ListNode.print(reverseKGroup(listA, 2));
    }
    ListNode leftNode = null; // 后驱节点

    /**
     * 反转列表
     */
    public ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head, nxt;
        while (cur != null) {
            // nxt赋值->cur.next转向->pre/cur后移
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 反转列表-递归法
     */
    public ListNode reverseRecursion(ListNode head) {
        // base case ：当一个节点或最后一个节点时，返回head
        if (head == null || head.next == null) {
            return head;
        }
        // 递归处理每个子问题
        ListNode last = reverseRecursion(head.next);
        /*** 处理子问题：调整指针指向 start ***/
        head.next.next = head;
        head.next = null;
        /*** 处理子问题 end ***/
        return last;
    }

    /**
     * 反转链表的前N个元素
     */
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 存放后半部分无需反转（或已反转完）的部分
            leftNode = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        // 调整指针指向
        head.next.next = head;
        head.next = leftNode;
        return last;
    }

    /**
     * 反转链表的[m,n]元素
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 当m=1等同反转前n个元素
        if (m == 1) {
            return reverseN(head, n);
        }
        // 当m > 1时，递归处理head.next
        head.next = reverseBetween(head.next, m-1, n - 1);
        return head;
    }

    /**
     * 反转链表中的元素，从元素a到元素b [a,b)
     *
     * 与反转整个链表相比，增加了cur != b的判断
     */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null, cur = a, nxt;
        while (cur != null && cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 每组k个元素，按组反转元素 0<k<=list.length，不满k个的一组不反转
     */
    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // [a,b)
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        // 反转这一组k个元素，[a,b)=>[b,a)
        ListNode newList = reverse(a, b);
        // 反转下一组k个元素，并拼接起来
        a.next = reverseKGroup(b, k);
        return newList;
    }

}
