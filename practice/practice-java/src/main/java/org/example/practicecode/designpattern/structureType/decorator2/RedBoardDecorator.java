package org.example.practicecode.designpattern.structureType.decorator2;

public class RedBoardDecorator extends ShapeDecorator {
    public RedBoardDecorator(Shape shape) {
        super(shape);
    }

    private void setRedBoard() {
        System.out.println("red circle set read board");
    }

    @Override
    public void draw() {
        targetShape.draw();
        setRedBoard();
    }
}
