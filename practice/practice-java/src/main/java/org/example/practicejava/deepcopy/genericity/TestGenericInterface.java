package org.example.practicejava.deepcopy.genericity;

/**
 * 测试泛型接口
 */
public class TestGenericInterface {
    public static void main(String[] args) {

    }

    /**
     * 测试泛型接口-1
     */
    public static void testGenericityInterface() {
        Generic<Integer> genericInteger = new Generic<Integer>(123456);
        Generic<String> genericString = new Generic<String>("HelloWorld");

        System.out.println("泛型测试：key=" + genericInteger.getKey());
        System.out.println("泛型测试：key=" + genericString.getKey());
    }
}
