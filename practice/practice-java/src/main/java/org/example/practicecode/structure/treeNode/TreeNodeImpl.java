package org.example.practicecode.structure.treeNode;


public class TreeNodeImpl {

    private void traverse(TreeNode root) {
        // 前序遍历
        traverse(root.left);
        // 中序遍历
        traverse(root.right);
        // 后序遍历
    }

    // 前序遍历
    public static void traverseBefore(TreeNode root) {
        if (null != root) {
            System.out.println(root.val);
            traverseBefore(root.left);
            traverseBefore(root.right);
        }
    }

    // 中序遍历
    public static void traverseMid(TreeNode root) {
        if (null != root) {
            traverseMid(root.left);
            System.out.println(root.val);
            traverseMid(root.right);
        }
    }

    // 后序遍历
    public static void traverseAfter(TreeNode root) {
        if (null != root) {
            traverseAfter(root.left);
            traverseAfter(root.right);
            System.out.println(root.val);
        }
    }
}
