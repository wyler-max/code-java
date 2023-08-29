package org.example.practice.practicecode.javalang.java8feature;

import org.junit.Test;

/**
 * 默认方法
 *
 * 简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。 我们只需在方法名前面加个 default 关键字即可实现默认方法。
 *
 * 是为了解决在接口中添加新方法，导致要修改所有的实现类的问题
 */
public class DefaultMethodTest {

    @Test
    public void testDefaultMethod() {
        Car car = new Car();
        car.print();
        // 因 Vehicle 接口中声明了default类型的blowHorn，所以 Vehicle的所有实现类都可以直接调用blowHorn方法
        // 也可以重写该方法
        car.blowHorn();
    }

    public interface Vehicle {
        static void print() {
            System.out.println("我是一辆车");
        }

        // 接口中添加1个新方法，声明为default类型
        default void blowHorn() {
            System.out.println("按喇叭！！！");
        }
    }

    public interface FourWheeler {
        static void print() {
            System.out.println("我是一辆四轮车");
        }
    }

    public class Car implements Vehicle, FourWheeler {
        public void print() {
            Vehicle.print();
            FourWheeler.print();
            System.out.println("我是一辆四轮汽车");
            Vehicle.super.blowHorn();
        }

        @Override
        public void blowHorn() {
            System.out.println("汽车按喇叭！！！");
        }
    }
}
