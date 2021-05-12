package org.example.practicejava.tmp;

public class  Model {
    private String stringA;
    private String stringB;

    public Model(String stringA, String stringB) {
        this.stringA = stringA;
        this.stringB = stringB;
    }

    public String getStringA() {
        return stringA;
    }

    public String getStringB() {
        return stringB;
    }

    @Override
    public String toString() {
        return "Model{" +
                "stringA='" + stringA + '\'' +
                ", stringB='" + stringB + '\'' +
                '}';
    }
}
