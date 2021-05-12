package org.example.practicecode.designpattern.visitor;

import com.example.designpattern.visitor.ComputerPartVisitor;

/**
 * 元素接口
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
