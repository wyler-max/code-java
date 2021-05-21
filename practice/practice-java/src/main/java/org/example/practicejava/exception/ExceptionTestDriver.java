package org.example.practicejava.exception;

public class ExceptionTestDriver {
    public static void main(String[] args) {

        try (FileOptionA fileOptionA = new FileOptionA(); FileOptionB fileOptionB = new FileOptionB()) {
            fileOptionA.open();
            // fileOptionA.read();
            fileOptionB.open();
            // fileOptionB.read();
        } catch (Exception e) {
            System.out.println("main excetion");
            System.out.println(e.getMessage());
            System.out.println("count=" + e.getSuppressed().length);
            for (Throwable throwable : e.getSuppressed()) {
                System.out.println(throwable.getMessage());
            }
        }
    }
}
