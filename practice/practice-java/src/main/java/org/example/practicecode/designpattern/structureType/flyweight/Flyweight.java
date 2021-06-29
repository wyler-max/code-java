package org.example.practicecode.designpattern.structureType.flyweight;

/**
 * 享元对象
 */
public class Flyweight implements IFlyweight {

    private String id;

    public Flyweight(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void print() {
        System.out.println("Flyweight.id= " + getId() + " ...");
    }
}
