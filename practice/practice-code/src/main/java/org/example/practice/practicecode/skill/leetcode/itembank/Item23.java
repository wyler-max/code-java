package org.example.practice.practicecode.skill.leetcode.itembank;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.example.practice.practicecode.skill.structure.linkList.ListNode;
import org.junit.Test;

/**
 * 合并多个升序链表
 *
 * 思路： 1、链表 head 排序比较 2、借助优先级队列 PriorityQueue，对接点排序 3、分治法
 */
public class Item23 {

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

        // 2-5-8
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(5);
        l3.next.next = new ListNode(8);

        ListNode[] lists = new ListNode[] {l1, l3, l2};
        // ListNode kLists = mergeKLists(lists);
        // ListNode kLists = mergeKLists2(lists);
        ListNode kLists = mergeKLists3(lists, 0, lists.length - 1);
        ListNode.print(kLists);
    }

    /**
     * 合并两个升序单链表
     */
    public ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode list = dummy;
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
        list.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    /**
     * 自主实现节点排序
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode list = dummy;
        Map<Integer, ListNode> map = new HashMap<>();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                map.put(i, lists[i]);
            }
        }
        while (!map.isEmpty()) {
            ListNode min = new ListNode(Integer.MAX_VALUE);
            int minKey = Integer.MAX_VALUE;
            for (Map.Entry<Integer, ListNode> entry : map.entrySet()) {
                ListNode node = entry.getValue();
                if (node != null) {
                    if (node.val < min.val) {
                        min = node;
                        minKey = entry.getKey();
                    }
                }
            }
            if (min.next == null) {
                map.remove(minKey);
            } else {
                map.put(minKey, min.next);
            }
            list.next = min;
            list = list.next;
        }
        return dummy.next;
    }

    /**
     * 利用优先级队列 PriorityQueue（二叉堆）进行节点排序
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode list = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, ((o1, o2) -> (o1.val - o2.val)));
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            if (node.next != null) {
                pq.add(node.next);
            }
            list.next = new ListNode(node.val);
            list = list.next;
        }
        return dummy.next;
    }

    /**
     * 分治法
     */
    public ListNode mergeKLists3(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }
        int mid = right + (right - left) >> 2;
        System.out.println(mid);
        return mergeList(mergeKLists3(lists, left, mid), mergeKLists3(lists, mid + 1, right));
    }
}
