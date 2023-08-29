package org.example.practice.practicecode.javalang.grammar.condition;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 优化 if-else 的8种方案
 */
public class ifelseTest {
    public static void main(String[] args) {

        // 6 表驱动法
        int value1 = 1;
        int value2 = 2;
        int value3 = 3;
        String param1 = "param1";
        String param2 = "param2";
        String param3 = "param3";

        Map<Integer, Function<String, String>> actionMappings = new HashMap<>();
        // 初始化
        actionMappings.put(value1, (param) -> doAction1(param));
        actionMappings.put(value2, (param) -> doAction2(param));
        actionMappings.put(value3, (param) -> doAction3(param));
        // 省略多余逻辑语句
        actionMappings.get(value1).apply(param1);
        actionMappings.get(value2).apply(param2);
        actionMappings.get(value3).apply(param3);

        // 8 策略模式+工厂方法
        String medalType = "guest";
        IMedalService medalService = MedalServicesFactory.getMedalService(medalType);
        medalService.showMedal();
    }

    private static String doAction1(String string) {
        System.out.println(string);
        return string;
    }

    private static String doAction2(String string) {
        System.out.println(string);
        return string;
    }

    private static String doAction3(String string) {
        System.out.println(string);
        return string;
    }
}
