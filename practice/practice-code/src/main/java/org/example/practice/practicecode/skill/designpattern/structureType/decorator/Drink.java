package org.example.practice.practicecode.skill.designpattern.structureType.decorator;

import lombok.Getter;
import lombok.Setter;

/**
 * 装饰者超类
 *
 * 被装饰的对象和装饰者都继承自同一个超类
 */
@Getter
@Setter
public abstract class Drink {
    public String description = "";
    private float price = 0f;

    public abstract float cost();
}
