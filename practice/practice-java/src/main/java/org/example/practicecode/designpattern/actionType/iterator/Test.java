package org.example.practicecode.designpattern.actionType.iterator;

/**
 * 迭代器模式，
 */
public class Test {
    public static void main(String[] args) {

        Waitress waitress = new Waitress();
        // 迭代器1
        waitress.addIterator(new CakeHouseMenu().getIterator());
        // 迭代器2
        waitress.addIterator(new DinerMenu().getIterator());
        waitress.printMenu();
    }
}
