package org.example.practicecode.designpattern.actionType.memento;



import java.util.ArrayList;
import java.util.List;

/**
 * 管理者
 */
public class CareTaker {
    private List<MementoIF> mementoList = new ArrayList<MementoIF>();

    public void add(MementoIF memento) {
        mementoList.add(memento);
    }

    public MementoIF get(int index) {
        return mementoList.get(index);
    }
}
