package org.example.practicecode.structure.treeNode;


import org.junit.Test;

public class TreeNNodeTest {
    @Test
    public void test() {
        TreeNNode tree = getTree();
        TreeNNodeImpl.traverse(tree);
    }

    private TreeNNode getTree() {
        TreeNNode treeNNode1 = new TreeNNode(1, null);
        TreeNNode treeNNode2 = new TreeNNode(2, null);
        TreeNNode treeNNode3 = new TreeNNode(3, null);
        TreeNNode treeNNode4 = new TreeNNode(4, null);
        TreeNNode treeNNode5 = new TreeNNode(5, null);
        TreeNNode treeNNode6 = new TreeNNode(6, null);
        TreeNNode treeNNode7 = new TreeNNode(7, null);

        TreeNNode[] children1 = new TreeNNode[3];
        children1[0] = treeNNode2;
        children1[1] = treeNNode3;
        children1[2] = treeNNode4;
        treeNNode1.setChildren(children1);

        TreeNNode[] children2 = new TreeNNode[3];
        children2[0] = treeNNode5;
        children2[1] = treeNNode6;
        children2[2] = treeNNode7;
        treeNNode3.setChildren(children2);
        return treeNNode1;
    }
}
