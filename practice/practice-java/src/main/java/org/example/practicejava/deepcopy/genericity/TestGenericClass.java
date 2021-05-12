package org.example.practicejava.deepcopy.genericity;

/**
 * 测试泛型类
 */
public class TestGenericClass {
    public static void main(String[] args) {
        testGenericityClass3();
    }

    /**
     * 测试泛型类-1
     */
    public static void testGenericityClass() {
        Generic<Integer> genericInteger = new Generic<Integer>(123456);
        Generic<String> genericString = new Generic<String>("HelloWorld");

        System.out.println("泛型测试：key=" + genericInteger.getKey());
        System.out.println("泛型测试：key=" + genericString.getKey());
    }

    /**
     * 测试泛型类-2
     *
     * 不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型
     */
    public static void testGenericityClass2() {
        Generic genericInteger = new Generic(123456);
        Generic genericString = new Generic("HelloWorld");
        Generic genericFloat = new Generic(10.10F);
        Generic genericBoolen = new Generic(false);

        System.out.println("泛型测试：key=" + genericInteger.getKey());
        System.out.println("泛型测试：key=" + genericString.getKey());
        System.out.println("泛型测试：key=" + genericFloat.getKey());
        System.out.println("泛型测试：key=" + genericBoolen.getKey());
    }

    private static void showKeyValue(Generic<Number> obj) {
        System.out.println("泛型测试：key=" + obj.getKey());
    }

    private static void showKeyValue2(Generic<?> obj) {
        System.out.println("泛型测试：key=" + obj.getKey());
    }

    public static void testGenericityClass3() {
        Generic<Integer> genericInteger = new Generic<Integer>(123);
        Generic<Number> genericNumber = new Generic<Number>(456);

        showKeyValue(genericNumber);
        //因类型不匹配会报错
        //showKeyValue(genericInteger);

        System.out.println("-------------------");

        showKeyValue2(genericNumber);
        showKeyValue2(genericInteger);
    }

}
