package org.example.practicecode.structure.treeNode;

import lombok.Data;

@Data
public class TreeNode {
    public int val;
    TreeNode left, right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
