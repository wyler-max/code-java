package org.example.practice.practicecode.skill.structure.utils;

import java.util.LinkedList;
import java.util.Stack;

import org.example.practice.practicecode.skill.structure.treeNode.TreeNNode;
import org.example.practice.practicecode.skill.structure.treeNode.TreeNode;

import com.google.common.collect.Lists;

/**
 * 树工具
 */
public class TreeUtil {

    /**
     * 构建一个二叉树
     *
     * <pre>
     *
     * 简单的二叉树
     *       1
     *     /    \
     *   2        3
     *  /  \     /  \
     * 4    5   6    7
     * </pre>
     * 
     * @param count
     *            节点个数
     * @return
     *
     */
    public static TreeNode buildBinaryTree(int count) {
        TreeNode root = new TreeNode(1, null, null);
        LinkedList<TreeNode> list = Lists.newLinkedList();
        list.add(root);
        int start = 2;
        while (list.size() > 0) {
            if (start > count) {
                break;
            }
            TreeNode node = list.removeFirst();
            if (node == null) {
                break;
            }
            if (node.left == null) {
                node.left = new TreeNode(start++, null, null);
                list.addLast(node.left);
            }
            if (node.right == null) {
                node.right = new TreeNode(start++, null, null);
                list.addLast(node.right);
            }
        }
        return root;
    }

    /**
     * 构建一个N叉树
     *
     * @param count
     *            节点个数
     * @param n
     *            分叉个数
     * @return
     */
    public static TreeNNode buildNTree(int count, int n) {
        TreeNNode root = new TreeNNode(1, new TreeNNode[n]);
        LinkedList<TreeNNode> list = new LinkedList<>();
        list.add(root);
        int start = 2;
        while (list.size() > 0) {
            if (start > count) {
                break;
            }
            TreeNNode node = list.removeFirst();
            for (int i = 0; i < n; i++) {
                node.children[i] = new TreeNNode(start++, new TreeNNode[n]);
                list.addLast(node.children[i]);
            }
        }
        return root;
    }

    /**
     * 前序遍历 root->root.left->root.right 1、2、4、5、3、6、7
     */
    public static void traverseBefore(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        traverseBefore(root.left);
        traverseBefore(root.right);
    }

    /**
     * 中序遍历 root.left->root->root.right 4、2、5、1、6、3、7
     */
    public static void traverseMid(TreeNode root) {
        if (root == null) {
            return;
        }
        traverseMid(root.left);
        System.out.println(root.val);
        traverseMid(root.right);
    }

    /**
     * 后序遍历 root.left->root.right->root 4、5、2、6、7、3、1
     */
    public static void traverseAfter(TreeNode root) {
        if (root == null) {
            return;
        }
        traverseAfter(root.left);
        traverseAfter(root.right);
        System.out.println(root.val);
    }

    /**
     * 深度优先遍历
     *
     * 利用的是栈 pop(root) / push(root.right)、push(root.left)
     */
    public static void traverseDeepFirst(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {
                System.out.println(pop.val);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }
    }

    // 深度优先遍历2，用递归替换栈 等于先序遍历
    public static void traverseDeepFirst2(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            traverseDeepFirst2(root.left);
            traverseDeepFirst2(root.right);
        }
    }

    /**
     * 广度优先遍历 (水平遍历)
     *
     * 利用的是队列 removeFirst(root) / add(root.left)、add(root.right)
     */
    public static void traverseLevelFirst(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (list.size() > 0) {
            TreeNode node = list.removeFirst();
            if (node != null) {
                System.out.println(node.val);
                if (node.left != null) {
                    list.addLast(node.left);
                }
                if (node.right != null) {
                    list.addLast(node.right);
                }
            }
        }
    }

    /**
     * N 叉树遍历
     */
    public static void traverseNNode(TreeNNode root) {
        if (root != null) {
            System.out.println(root.val);
            for (TreeNNode child : root.children) {
                traverseNNode(child);
            }
        }
    }
}
