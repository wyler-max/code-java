package org.example.practice.practicecode.skill.designpattern.actionType.memento;

/**
 * 备忘录模式，保存一个对象的某个状态，以便在适当的时候恢复对象
 */
public class Test {

    public static void main(String[] args) {
        String state1 = "ok";
        String state2 = "oops";
        Originator o = new Originator();
        CareTaker careTaker = new CareTaker();
        // 设置状态1
        o.setState(state1);
        System.out.println("设置状态1：" + o.getState());
        careTaker.add(o.saveToMemento());
        System.out.println("从备忘录中取值：" + o.getStateFromMemento(careTaker.get(0)));
        System.out.println("===============");

        // 设置状态2
        o.setState(state2);
        System.out.println("设置状态2：" + o.getState());
        careTaker.add(o.saveToMemento());
        System.out.println("从备忘录中取值：" + o.getStateFromMemento(careTaker.get(1)));
        System.out.println("===============");

        // 恢复状态1
        o.setState(o.getStateFromMemento(careTaker.get(0)));
        System.out.println("恢复后的状态：" + o.getState());
    }
}
