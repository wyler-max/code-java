package org.example.practicejava.deepcopy.invoke;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author wangyulin
 * @date 2020/7/2
 */
public class MethodInvoke {
    public static void main(String[] args) throws Exception {
        Method animalMethod = Animal.class.getMethod("print");
        Method catMethod = Cat.class.getDeclaredMethod("print");

        Animal animal = new Animal();
        Cat cat = new Cat();

        animalMethod.invoke(animal);
        animalMethod.invoke(cat);

        //catMethod.invoke(animal);
        catMethod.invoke(cat);

        Method[] declaredMethods = Animal.class.getDeclaredMethods();
        System.out.println(Arrays.toString(declaredMethods));
    }
}
