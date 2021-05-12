package org.example.practicecode.structure.treeNode;

import lombok.Data;

@Data
public class TreeNNode {
    public int val;
    TreeNNode[] children;

    public TreeNNode(int val, TreeNNode[] children) {
        this.val = val;
        this.children = children;
    }
}
