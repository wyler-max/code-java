package org.example.practicecode.designpattern.bridge;

import com.example.designpattern.bridge.Software;

/**
 *
 */
public abstract class Phone {

    protected Software software;

    public void setSoftware(Software software) {
        this.software = software;
    }

    public abstract void run();
}
