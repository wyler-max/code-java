package org.example.practicecode.designpattern.bridge;

import com.example.designpattern.bridge.Software;

/**
 *
 */
public class Camera implements Software {
    @Override
    public void run() {
        System.out.println("run camera");
    }
}
