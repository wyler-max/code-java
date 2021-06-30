package org.example.practicecode.designpattern.structureType.decorator;

/**
 * 装饰者
 *
 * 装饰者不仅要考虑自身，还要考虑被它修饰的对象，它是在被修饰的对象上添加修饰。
 *
 * 例如 </br>
 * 咖啡里面加咖啡豆，价格为coffee+soy。 </br>
 * 咖啡里面加牛奶，价格为coffee+milk。 </br>
 * 咖啡里面加牛奶加巧克力，价格为coffee+milk+chocolate
 */
public class Decorator extends Drink {
    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "-" + super.getPrice() + " && " + drink.getDescription();
    }
}
