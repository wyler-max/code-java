package org.example.practicecode.leetcode.itembank;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;

/**
 * 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 */
public class Item20 {

    @Test
    public void demo() {
        String str = "{[]}";
        System.out.println(isValid2(str));
    }

    /**
     * (({}([]))())
     *
     */
    private boolean isValid(String str) {
        if (str.isEmpty()) {
            return true;
        }
        if (str.length() % 2 != 0) {
            return false;
        }
        Map<Character, Integer> strMap = new HashMap<>();
        strMap.put('(', 1);
        strMap.put(')', -1);
        strMap.put('{', 2);
        strMap.put('}', -2);
        strMap.put('[', 3);
        strMap.put(']', -3);
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (strMap.get(str.charAt(i)) > 0) {
                stack.push(str.charAt(i));
            } else {
                if (i < 1) {
                    return false;
                }
                if (stack.empty() || strMap.get(str.charAt(i)) + strMap.get(stack.pop()) != 0) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    private boolean isValid2(String str) {
        if (str.isEmpty()) {
            return true;
        }
        if (str.length() % 2 != 0) {
            return false;
        }
        Map<Character, Integer> strMap = new HashMap<>();
        strMap.put('(', 1);
        strMap.put(')', -1);
        strMap.put('{', 2);
        strMap.put('}', -2);
        strMap.put('[', 3);
        strMap.put(']', -3);
        char[] charArr = new char[str.length()+1];
        int p = 1;
        for (char c : str.toCharArray()) {
            if (strMap.get(c) > 0) {
                charArr[p++] = c;
            } else {
                if (strMap.get(charArr[c]) + strMap.get(charArr[--p]) != 0) {
                    return false;
                }
            }
        }
        return p == 1;
    }


    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        String ret = stack.peek();
        System.out.println(ret);
        System.out.println(stack);
    }

}
