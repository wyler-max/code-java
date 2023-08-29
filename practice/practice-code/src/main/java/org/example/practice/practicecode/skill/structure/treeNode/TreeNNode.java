package org.example.practice.practicecode.skill.structure.treeNode;

import lombok.Data;

/**
 * N 叉树节点
 */
@Data
public class TreeNNode {
    public int val;
    public TreeNNode[] children;

    public TreeNNode(int val, TreeNNode[] children) {
        this.val = val;
        this.children = children;
    }
}
