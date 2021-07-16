package org.example.practicejava.grammar.loop;

import java.util.Collections;
import java.util.List;

public class LoopEmptyTest {
    public static void main(String[] args) {
        List<Long> list = Collections.emptyList();

        System.out.println("stream");
        list.stream().forEach(x -> {
            System.out.println(x);
        });
        System.out.println("forEach");
        list.forEach(x -> {
            System.out.println(x);
        });
        System.out.println("for");
        for (Long x : list) {
            System.out.println(x);
        }
    }
}
