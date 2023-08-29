package org.example.practice.practicecode.skill.leetcode.singlelist;

import org.example.practice.practicecode.skill.structure.linkList.ListNode;
import org.junit.Test;

/**
 * 遍历链表
 */
public class Traverse {

    @Test
    public void testTraverse() {
        ListNode n6 = new ListNode(6);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);

        ListNode nr1 = new ListNode(12);
        ListNode nr2 = new ListNode(2, nr1);
        ListNode nr3 = new ListNode(3, nr2);

        ListNode n3 = new ListNode(3, nr3);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        ListNode head = n1;
        ListNode.print(head);

        System.out.println(isPalindromeV2(head));
        // ListNode.print(reverse(head));
        ListNode.print(head);
    }

    /**
     * 链表的遍历框架，和树类似。 链表兼具递归结构，树结构不过是链表的衍生。
     */
    public void traverseFrame(ListNode head) {
        if (head == null) {
            return;
        }
        // 前序遍历
        // System.out.println(head);
        traverseFrame(head.next);
        // 后续遍历
        System.out.println(head);
    }

    ListNode left;

    /**
     * 判断链表是否回文 时间复杂度：O(n)；空间复杂度：O(n)
     */
    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    /**
     * 利用链表的后续遍历（递归特性），倒叙获取链表的节点
     */
    public boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        traverse(right.next);
        boolean res = (left.val == right.val);
        left = left.next;
        return res;
    }

    /**
     * 判断链表是否回文 时间复杂度：O(n)；空间复杂度：O(1)
     */
    public boolean isPalindromeV2(ListNode head) {
        // 快慢指针找到中点，分割链表left、right
        // 反转right，与left比较
        // 反转right，恢复链表
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        ListNode left = head;
        ListNode right = reverse(slow);
        ListNode q = right;
        while (right != null && (left.val == right.val)) {
            left = left.next;
            right = right.next;
        }
        reverse(q);
        return right == null;
    }

    /**
     * 反转链表
     */
    public ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head, nxt;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

}
