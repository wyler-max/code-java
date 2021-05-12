package org.example.practicecode.designpattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂，为了避免享元对象被重复创建，我们使用HashMap中的key值保证其唯一
 */
public class FlyweightFactory {

    private Map<String, IFlyweight> flyweightMap = new HashMap<>();

    public IFlyweight getFlyweight(String str) {
        IFlyweight flyweight = flyweightMap.get(str);
        if (flyweight == null) {
            flyweight = new Flyweight(str);
            flyweightMap.put(str, flyweight);
        }
        return flyweight;
    }

    public int getFlyweightMapSize() {
        return flyweightMap.size();
    }
}
