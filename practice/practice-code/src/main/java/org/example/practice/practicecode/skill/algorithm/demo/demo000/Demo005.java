package org.example.practice.practicecode.skill.algorithm.demo.demo000;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * 双指针技巧
 *  1、快慢指针：解决链表中的问题，比如链表中是否有环
 *  2、左右指针：解决数组或字符串中的问题，比如二分查找
 */
public class Demo005 {

    @Test
    public void demo1() {
        Node head = getListNodeCycle();
        System.out.println(hasCycle(head));
    }

    /**
     * 判断链表中是否有环
     */
    private boolean hasCycle(Node head) {
        Node fast, slow;
        fast = slow = head;
        List<Integer> list = Lists.newArrayList();
        while (fast != null && fast.next != null) {
            list.add((Integer) slow.data);
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                System.out.println("相遇节点：" + fast.data);
                System.out.println(list);
                return true;
            }
        }
        System.out.println(list);
        return false;
    }

    @Test
    public void demo2() {
        Node head = getListNodeCycle();
        if (hasCycle(head)) {
            Node node = deleteCycle(head);
            System.out.println(node.data);
        }
    }
    /**
     * 删除链表中的环
     */
    public Node deleteCycle(Node head) {
        Node fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    @Test
    public void demo3() {
        Node head = getListNodeLink();
        Node ret = searchLastNNode(head, 3);
        System.out.println(ret.data);
    }

    public Node searchLastNNode(Node head, int n) {
        Node fast, slow;
        fast = slow = head;
        if (n < 0) {
            return null;
        }
        while (fast != null) {
            if (n > 0) {
                fast = fast.next;
                n--;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public class Node {
        public Object data;
        public Node next;

        public Node(Object o) {
            this.data = o;
        }
    }

    /**
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 3
     */
    private Node getListNodeCycle() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n3;
        return n1;
    }

    /**
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
     */
    private Node getListNodeLink() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = null;
        return n1;
    }

    private void loopListNode(Node node) {
        if (node == null) {
            System.out.println("链表长度为0");
            return;
        }
        List<Integer> nodeList = Lists.newArrayList();
        int limit = 0;
        while (node.data != null && limit < 100) {
            limit++;
            nodeList.add((Integer)node.data);
            if (node.next == null) {
                break;
            }
            node = node.next;
        }
        System.out.println(nodeList);
    }

}
