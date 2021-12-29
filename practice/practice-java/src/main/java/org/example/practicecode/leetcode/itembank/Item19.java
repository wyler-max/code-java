package org.example.practicecode.leetcode.itembank;

import org.example.practicecode.structure.linkList.ListNode;
import org.junit.Test;

/**
 * 查找、删除单链表中的节点
 */
public class Item19 {

    @Test
    public void demo() {
        ListNode head = new ListNode(1);
        ListNode list = head;
        /*list.next = new ListNode(2,
                new ListNode(3,
                        new ListNode(4,
                                new ListNode(5))));*/
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        list.next = n2;
        n5.next = n3;
        if (hasCycle(head)) {
            System.out.println("环起点：" + detectCycle(head));
        } else {
            System.out.println("没有环");
        }
    }

    /**
     * 查找链表的倒数第N个节点
     */
    public ListNode findNthFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        for(int i = 0; i < n; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        while(p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 删除倒数第N个节点
     * 思路：先查找，再删除
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟节点构造新链表，方便对head节点操作
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 查找新链表的倒数第N+1节点，n节点的父节点，方便对n节点操作
        ListNode node = findNthFromEnd(dummy, n+1);
        // 去掉n节点
        node.next = node.next == null ? null : node.next.next;
        return dummy.next;
    }

    /**
     * 思路：查找并删除
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode p1 = head;
        for(int i = 0; i<=n; i++) {
            if(p1 == null){
                return head.next;
            }
            p1 = p1.next;
        }
        ListNode p2 = head;
        while(p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode node = p2.next;
        p2.next = node == null ? null : node.next;
        return head;
    }

    /**
     * 单链表的中点
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 判断链表中是否有环
     *
     * 思路：快慢指针，同起点，异步长
     *
     * 拓展：为什么步长是2倍，而不是3、4、5、6倍
     * 1、证真：数学归纳法可证明2倍可以
     * 2、证伪：设环周长为c，环中两点距离为k，慢指A针步长为1、快指B针步长为x，那么每次B追及A的距离是x-1。
     * 则追及成功时实际追及距离s必然是 x-1的整数倍，即 s/(x-1) s能被x-1整除，当且仅当x=2时，不论s如何取值，整除都成立。
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找单列表中环的起点
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
