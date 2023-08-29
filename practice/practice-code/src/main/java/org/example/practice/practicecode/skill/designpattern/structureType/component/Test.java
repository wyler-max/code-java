package org.example.practice.practicecode.skill.designpattern.structureType.component;

/**
 * 组合模式测试类
 */
public class Test {
    public static void main(String[] args) {
        Component composite = new Composite();
        // 叶子集合
        composite.add(new Leaf("leaf1"));
        composite.add(new Leaf("leaf2"));
        composite.add(new Leaf("leaf3"));
        composite.add(new Leaf("leaf4"));
        composite.add(new Leaf("leaf5"));
        // 获取 leaf3
        composite.getChild(2).operation();
        // 尝试删除 leaf4
        System.out.println("删除leaf4 real");
        Component leaf4Inner = composite.getChild(3);
        System.out.println(leaf4Inner);
        composite.remove(leaf4Inner);
        composite.operation();
    }
}
