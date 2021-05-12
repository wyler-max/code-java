package org.example.practicecode.designpattern.iterator;

import com.example.designpattern.iterator.CakeHouseMenu;
import com.example.designpattern.iterator.DinerMenu;
import com.example.designpattern.iterator.Iterator;
import com.example.designpattern.iterator.Waitress;

/**
 * 迭代器模式测试类
 */
public class Test {
    public static void main(String[] args) {

        Iterator iterator1 = new CakeHouseMenu().getIterator();
        /*while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }*/

        Waitress waitress = new Waitress();
        waitress.addIterator(iterator1);

        Iterator iterator2 = new DinerMenu().getIterator();
        waitress.addIterator(iterator2);
        waitress.printMenu();
    }
}
