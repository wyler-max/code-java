package org.example.practicecode.designpattern.visitor;

import com.example.designpattern.visitor.ComputerPart;
import com.example.designpattern.visitor.ComputerPartVisitor;

/**
 * 元素实体类
 */
public class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
