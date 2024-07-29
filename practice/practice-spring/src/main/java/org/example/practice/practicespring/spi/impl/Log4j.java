package org.example.practice.practicespring.spi.impl;

import org.example.practice.practicespring.spi.Log;

public class Log4j implements Log {
    @Override
    public void log(String msg) {
        System.out.println("Load Log4j:" + msg);
    }
}
