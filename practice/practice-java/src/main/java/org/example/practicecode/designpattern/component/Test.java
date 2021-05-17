package org.example.practicecode.designpattern.component;

/**
 * 组合模式测试类
 */
public class Test {
    public static void main(String[] args) {

        Component composite = new Composite();

        composite.add(new Leaf("leaf1"));
        composite.add(new Leaf("leaf2"));
        composite.add(new Leaf("leaf3"));
        composite.add(new Leaf("leaf4"));
        composite.add(new Leaf("leaf5"));

        composite.getChild(2).operation();

        System.out.println("删除leaf4");
        Component leaf4 = new Leaf("leaf4");
        System.out.println(leaf4);
        composite.remove(leaf4);
        composite.operation();

        System.out.println("删除leaf4 real");
        Component leaf4Inner = composite.getChild(3);
        System.out.println(leaf4Inner);
        composite.remove(leaf4Inner);
        composite.operation();

    }
}
