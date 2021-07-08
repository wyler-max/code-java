package org.example.practice.practicespring.spi.impl;

import org.example.practice.practicespring.spi.Log;

public class Logback implements Log {
    @Override
    public void log(String msg) {
        System.out.println("Logback:" + msg);
    }
}
