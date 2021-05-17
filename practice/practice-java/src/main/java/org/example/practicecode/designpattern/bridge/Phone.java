package org.example.practicecode.designpattern.bridge;

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
