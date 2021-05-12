package org.example.practicejava.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Function<? super T,? extends R> 代表一个方法对象
 * 该方法对象定义的是被T类型对象调用处理对象本身，返回R类型对象
 *
 * 或者说
 *
 * 该方法是其他类型对象调用(普通方法)或其他类(静态方法)调用 处理T类型对象，返回R类型对象
 */
public class FunctionTest {

    private String s;

    public FunctionTest() {

    }
    public FunctionTest(String s) {
        this.s = s;
    }

    public static void main(String[] args) {
        // 测试1
        //new FunctionTest().go();
        // 测试2
        new FunctionTest("99999").testf(FunctionTest::sayTest);
    }

    public void go() {
        List<String> list = new ArrayList<String>();
        list.add("111");
        list.add("222");
        list.add("333");
        System.out.println("list: ");
        list.forEach(System.out::println);

        List<Integer> list2 = list.stream().map(FunctionTest::string2Integer).collect(Collectors.toList());
        System.out.println("list2: ");
        list2.forEach(System.out::println);
        List<Integer> list3 = list.stream().map(new FunctionTest()::string2Integer2).collect(Collectors.toList());
        System.out.println("list3: ");
        list3.forEach(System.out::println);
        List<FunctionTest> listTest = new ArrayList<>();
        listTest.add(new FunctionTest("111"));
        listTest.add(new FunctionTest("222"));
        listTest.add(new FunctionTest("333"));
        List<Integer> list4 = listTest.stream().map(FunctionTest::getS).collect(Collectors.toList());
        System.out.println("list4: ");
        list4.forEach(System.out::println);
    }

    public static Integer string2Integer(String str) {
        return Integer.valueOf(str);
    }
    public Integer string2Integer2(String str) {
        return Integer.valueOf(str);
    }
    public Integer getS() {
        return Integer.valueOf(s);
    }

    public void testf(Consumer<? super FunctionTest> handleM) {
        handleM.accept(this);
    }
    public void sayTest() {
        System.out.println(this.getS());
    }
}
