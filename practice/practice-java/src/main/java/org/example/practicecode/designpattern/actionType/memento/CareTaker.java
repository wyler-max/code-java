package org.example.practicecode.designpattern.actionType.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理者，管理备忘录列表
 */
public class CareTaker {
    private List<IMemento> mementoList = new ArrayList<IMemento>();

    public void add(IMemento memento) {
        mementoList.add(memento);
    }

    public IMemento get(int index) {
        return mementoList.get(index);
    }
}
