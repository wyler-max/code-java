package org.example.practice.practicecode.skill.leetcode.itembank;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 罗马数字阿拉伯数字
 */
public class Item9 {

    @Test
    public void demo() {

    }

    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * 规则：
     * 1、通常情况下，罗马数字中小的数字在大的数字的右边。
     * 2、但也存在特例，例如 4 不写做 IIII，而是 IV。
     * 3、数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
     * 4、同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *      I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     *      X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     *      C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     *
     * 示例 1:
     * 输入: "III"
     * 输出: 3
     *
     * 示例 2:
     * 输入: "IV"
     * 输出: 4
     *
     * 示例 3:
     * 输入: "IX"
     * 输出: 9
     *
     * 示例 4:
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     *
     * 示例 5:
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *
     * minNum: I、X、C
     * MaxNum: V、L、D、M
     */
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        int ret = 0;
        for (int i = 0; i < s.length() -1; i++) {
            if (romanMap.get(s.charAt(i)) < romanMap.get(s.charAt(i+1))) {
                ret += (-romanMap.get(s.charAt(i)));
            } else {
                ret += romanMap.get(s.charAt(i));
            }
        }
        return ret + romanMap.get(s.charAt(s.length()-1));
    }

    // I， V， X， L，C，D 和 M
    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

}
