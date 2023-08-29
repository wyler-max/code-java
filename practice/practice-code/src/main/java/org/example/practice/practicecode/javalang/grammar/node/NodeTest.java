package org.example.practice.practicecode.javalang.grammar.node;

/**
 * @author wangyulin
 * @date 2021/7/8
 */
public class NodeTest {
    public static void main(String[] args) {
        Node<Integer, String> node3 = new Node<>(1, 3, "节点3", null);
        Node<Integer, String> node2 = new Node<>(1, 2, "节点2", node3);
        Node<Integer, String> node1 = new Node<>(1, 1, "节点1", node2);
    }
}
