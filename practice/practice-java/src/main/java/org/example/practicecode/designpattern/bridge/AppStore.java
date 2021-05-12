package org.example.practicecode.designpattern.bridge;

import com.example.designpattern.bridge.Software;

/**
 *
 */
public class AppStore implements Software {
    @Override
    public void run() {
        System.out.println("run app store");
    }
}
