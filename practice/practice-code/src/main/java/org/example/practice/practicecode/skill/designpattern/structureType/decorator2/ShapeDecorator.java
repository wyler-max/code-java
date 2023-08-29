package org.example.practice.practicecode.skill.designpattern.structureType.decorator2;

public abstract class ShapeDecorator implements Shape {

    protected Shape targetShape;

    public ShapeDecorator(Shape targetShape) {
        this.targetShape = targetShape;
    }

    @Override
    public void draw() {
        this.targetShape.draw();
    }
}
