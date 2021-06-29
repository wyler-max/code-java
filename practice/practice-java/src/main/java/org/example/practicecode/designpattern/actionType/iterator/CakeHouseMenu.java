package org.example.practicecode.designpattern.actionType.iterator;

import java.util.ArrayList;

/**
 *
 */
public class CakeHouseMenu {

    private ArrayList<MenuItem> menuItems;

    public CakeHouseMenu() {
        this.menuItems = new ArrayList<MenuItem>();

        this.menuItems.add(new MenuItem("KFC Cake Breakfast","boiled eggs&toast&cabbage",true,3.99f));
        this.menuItems.add(new MenuItem("MDL Cake Breakfast","fried eggs&toast",false,3.59f));
        this.menuItems.add(new MenuItem("Stawberry Cake","fresh stawberry",true,3.29f));
        this.menuItems.add(new MenuItem("Regular Cake Breakfast","toast&sausage",true,2.59f));
    }

    public Iterator getIterator() {
        return new CakeHouseIterator();
    }

    class CakeHouseIterator implements Iterator {

        private int position = 0;
        @Override
        public boolean hasNext() {
            if (position < menuItems.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            MenuItem menuItem = menuItems.get(position);
            position++;
            return menuItem;
        }
    }

}
