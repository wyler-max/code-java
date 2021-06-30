package org.example.practicecode.designpattern.actionType.iterator;

import java.util.ArrayList;

import com.google.common.collect.Lists;

/**
 * 服务员
 */
public class Waitress {

    private ArrayList<Iterator> iterators = Lists.newArrayList();

    public Waitress() {}

    public void addIterator(Iterator iterator) {
        iterators.add(iterator);
    }

    public void printMenu() {
        Iterator iterator;
        MenuItem menuItem;
        for (int i = 0; i < iterators.size(); i++) {
            System.out.println("迭代器: " + (i + 1));
            iterator = iterators.get(i);
            while (iterator.hasNext()) {
                menuItem = (MenuItem)iterator.next();
                System.out.println(menuItem);
            }
        }
    }

    public void printBreakfastMenu() {

    }

    public void printLunchMenu() {

    }

    public void printVegetableMenu() {

    }
}
