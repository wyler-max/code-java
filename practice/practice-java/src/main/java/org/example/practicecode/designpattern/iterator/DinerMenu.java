package org.example.practicecode.designpattern.iterator;

/**
 *
 */
public class DinerMenu {

    private final static int MAX_ITEM_SIZE = 5;
    private int numberOfMenu = 0;
    private MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEM_SIZE];
        addMenuItem(new MenuItem("vegetable Blt", "bacon&lettuce&tomato&cabbage", true, 3.58f));
        addMenuItem(new MenuItem("Blt", "bacon&lettuce&tomato", false, 3.00f));
        addMenuItem(new MenuItem("bean soup", "bean&potato salad", true, 3.28f));
        addMenuItem(new MenuItem("hotdog", "onions&cheese&bread", false, 3.05f));
    }

    private void addMenuItem(MenuItem menuItem) {
        if (numberOfMenu >= MAX_ITEM_SIZE) {
            System.out.println("menu is full!");
        } else {
            menuItems[numberOfMenu] = menuItem;
            numberOfMenu++;
        }
    }

    public Iterator getIterator() {
        return new DinerIterator();
    }

    class DinerIterator implements Iterator {

        private int position = 0;
        @Override
        public boolean hasNext() {
            if (position < numberOfMenu) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            MenuItem menuItem = menuItems[position];
            position++;
            return menuItem;
        }
    }
}
