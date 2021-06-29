package org.example.practicecode.designpattern.structureType.component;

import java.util.ArrayList;

import com.google.common.collect.Lists;

/**
 * 树枝
 */
public class Composite implements Component {

    private ArrayList<Component> children = Lists.newArrayList();

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public Component getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }
}
