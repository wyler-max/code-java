package org.example.practicejava.javaBase.collection.common;

import java.util.Comparator;

/**
 * 根据字符串长度比较
 */
public class CompareByLength implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {
        int num = str1.length() - str2.length();
        return num == 0? str1.compareTo(str2) : num;
    }
}
