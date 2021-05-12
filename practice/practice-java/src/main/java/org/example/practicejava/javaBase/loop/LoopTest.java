package org.example.practicejava.javaBase.loop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 各种循环实例
 * for/foreach/forEach
 * do...while() / while()
 * Iterator
 *
 * switch
 */
public class LoopTest {

    private List<String> list = new ArrayList<>();
    private HashMap hashMap = new HashMap();

    private void initData() {
        list.add("赵云");
        list.add("黄忠");
        list.add("马超");
        list.add("关羽");
        list.add("张飞");
    }

    /**
     * Loop List -- for
     */
    @Test
    public void testLoopListFor() {
        initData();
    }

    /**
     * for
     */
    @Test
    public void testLoopFor() {
        int sum = 0;
        for (int i=0; i <= 100; i++) {
            sum += i;
        }
        System.out.println("sum: " + sum);
    }

    /**
     * while()
     */
    @Test
    public void testLoopWhile() {
        int range = 1;
        while (range <= 100) {
            if (range % 7 == 0) {
                System.out.println("100以内能被7整除的数：" + range);
            }
            range++;
        }
    }

    /**
     * do...while()
     */
    @Test
    public void testLoopDoWhile() {
        int range = 1;
        do {
            if (range % 3 == 0 && range %7 == 0) {
                System.out.println("100以内能被3和7整除的数：" + range);
            }
            range++;
        }while (range <= 100);
    }

    /**
     * switch - 通过psvm方式测试
     */
    @Test
    public void testLoopSwitch() {
        System.out.println("请选择星期几：");
        System.out.println("1.星期一，2.星期二，3.星期三，4.星期四，5.星期五，67.周末");
        Scanner scanner = new Scanner(System.in);
        int day = scanner.nextInt();
        switch (day) {
            case 1:
                System.out.println("今天是星期一");break;
            case 2:
                System.out.println("今天是星期二");break;
            case 3:
                System.out.println("今天是星期三");break;
            case 4:
                System.out.println("今天是星期四");break;
            case 5:
                System.out.println("今天是星期五");break;
            case 6:
            case 7:
                System.out.println("今天是周末");break;
            default:
                System.out.println("日期错误");break;
        }
    }

}
