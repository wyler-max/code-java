package org.example.practicecode.test;

import com.fasterxml.jackson.databind.util.LinkedNode;
import org.example.practicecode.structure.linkList.ListNode;
import org.example.practicecode.structure.utils.LinkListUtil;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author wangyulin
 * @date 2022/7/26
 */
public class TestLinkList {

    @Test
    public void item21MergeSortedLinkList() {
        ListNode l1 = LinkListUtil.buildLinkList(new int[]{1,2,4});
        ListNode l2 = LinkListUtil.buildLinkList(new int[]{1,3,4});

        //LinkListUtil.traverseRecursion(mergeSortedLinkList(l1, l2));

        ListNode partitionNode = LinkListUtil.buildLinkList(new int[]{1,4,3,2,5,2});
        // LinkListUtil.traverseRecursion(partition(partitionNode, 3));

        ListNode l3 = LinkListUtil.buildLinkList(new int[]{2,3,4});
        ListNode[] list = new ListNode[] {l1, l2, l3};
        LinkListUtil.traverse(l1);
        LinkListUtil.traverse(l2);
        LinkListUtil.traverse(l3);
        System.out.println("*****");
        LinkListUtil.traverse(mergeKLists(list));
    }

    /**
     * 合并两个有序链表
     */
    public static ListNode mergeSortedLinkList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }

    /**
     * 单链表分解
     * 将单链表拆分成 l1/l2 2个链表；对应指针p1/p2
     * 遍历单链表：
     * case p.val <  x：p1.next = p
     * case p.val >= x：p2.next = p
     * 拼接 p1.next = l2.next
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2;
        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            ListNode temp = p.next;
            p.next = null;
            p = temp;
        }
        p1.next = l2.next;
        return l1.next;
    }

    /**
     * 合并多个有序链表
     * 1、构造有序队列
     * 2、将各链表的head添加到有序队列中
     * 3、从有序队列中取值，并更新node
     */
    public static ListNode mergeKLists(ListNode[] list) {
        if (list.length <= 0) {
            return null;
        } else if (list.length == 1) {
            return list[0];
        }
        Queue<ListNode> pq = new PriorityQueue<ListNode>(list.length, Comparator.comparingInt(a -> a.val));
        for (ListNode head : list) {
            if (head != null) {
                pq.add(head);
            }
        }
        System.out.println(pq.size());
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            p.next = node;
            System.out.println("node=" + node.val);
            if (node.next != null) {
                pq.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }

    @Test
    public void testfindFromEnd() {
        ListNode head = LinkListUtil.buildLinkList(new int[]{1,2,3,4,5,6,7});
        System.out.println(findFromEnd(head, 4));
    }

    /**
     * 查找链表的倒数第N个节点
     */
    public static ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    @Test
    public void testremoveNodeFromEnd() {
        ListNode head = LinkListUtil.buildLinkList(new int[]{1,2,3,4,5,6,7});
        LinkListUtil.traverse(removeNodeFromEnd(head, 4));
    }

    public static ListNode removeNodeFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = findFromEnd(head, k+1);
        p.next = p.next.next;
        return dummy.next;
    }

    @Test
    public void testMiddleNode() {
        ListNode head = LinkListUtil.buildLinkList(new int[]{1,2,3,4,5});
        System.out.println(middleNode(head));
    }

    /**
     * 查找单链表的中间点 快慢指针
     */
    public static ListNode middleNodeV1(ListNode head) {
        ListNode fast = head, slow = head;
        int l1 = 0, l2 = 0;
        while (fast != null) {
            if ((l1/2) > l2) {
                slow = slow.next;
                l2++;
            }
            l1++;
            fast = fast.next;
        }
        return slow;
    }

    public static ListNode middleNode(ListNode head) {
        ListNode fast = new ListNode(-1, head), slow = new ListNode(-1, head);
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(7/3);
        /**
         * 1
         * 2
         */
    }
}
