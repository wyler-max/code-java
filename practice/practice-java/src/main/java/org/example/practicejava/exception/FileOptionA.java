package org.example.practicejava.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UTFDataFormatException;

public class FileOptionA implements AutoCloseable {

    public void open() throws FileNotFoundException {
        System.out.println(this.getClass().getName() + " " + Thread.currentThread().getStackTrace()[1].getMethodName());
        throw new FileNotFoundException(this.getClass().getName() + " open FileNotFoundException");
    }

    public void read() throws UTFDataFormatException {
        System.out.println(this.getClass().getName() + " " + Thread.currentThread().getStackTrace()[1].getMethodName());
        throw new UTFDataFormatException(this.getClass().getName() + " read UTFDataFormatException");
    }

    @Override
    public void close() throws Exception {
        System.out.println(this.getClass().getName() + " " + Thread.currentThread().getStackTrace()[1].getMethodName());
        throw new IOException(this.getClass().getName() + " close IOException && collect resource");
    }
}
