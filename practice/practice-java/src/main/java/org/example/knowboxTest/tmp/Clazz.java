package org.example.knowboxTest.tmp;

public class Clazz implements IClazz {
    private String str = "default";

    public Clazz(String str) {
        this.str = str;
        System.out.println(this + " str=" + str);
    }

    @Override
    public String getClazzStr() {
        System.out.println("str=" + str);
        return str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
