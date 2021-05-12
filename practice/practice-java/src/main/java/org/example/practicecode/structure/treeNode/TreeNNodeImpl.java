package org.example.practicecode.structure.treeNode;


public class TreeNNodeImpl {

    public static void traverse(TreeNNode root) {
        if (null != root) {
            System.out.println(root.val);
            if (null == root.children) {
                return;
            }
            for (TreeNNode child: root.children) {
                traverse(child);
            }
        }
    }
}
