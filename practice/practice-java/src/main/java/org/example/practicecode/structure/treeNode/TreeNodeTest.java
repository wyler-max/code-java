package org.example.practicecode.structure.treeNode;


import org.junit.Test;

public class TreeNodeTest {

    @Test
    public void test() {
        TreeNode root = this.getTree();
        //TreeNodeImpl.traverseBefore(root);
        //TreeNodeImpl.traverseMid(root);
        TreeNodeImpl.traverseAfter(root);
    }

    private TreeNode getTree() {
        TreeNode treeNode1 = new TreeNode(1, null, null);
        TreeNode treeNode2 = new TreeNode(2, null, null);
        TreeNode treeNode3 = new TreeNode(3, null, null);
        TreeNode treeNode4 = new TreeNode(4, null, null);
        TreeNode treeNode5 = new TreeNode(5, null, null);
        TreeNode treeNode6 = new TreeNode(6, null, null);

        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        return treeNode1;
    }
}
