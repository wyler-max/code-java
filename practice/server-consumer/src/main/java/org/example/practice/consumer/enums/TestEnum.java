package org.example.practice.consumer.enums;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public enum TestEnum {
    VAR_1(1, "var_1"), VAR_2(2, "var_2"), VAR_3(3, "var_3"), VAR_4(4, "var_4"), VAR_5(5, "var_5"), VAR_6(6, "var_6"),
    VAR_7(7, "var_7"), VAR_8(8, "var_8"), VAR_9(9, "var_9  %s - %s"), VAR_10(10, "var_10 %s - %s");

    @Setter
    int code;
    @Setter
    String desc;

    TestEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static void loopEnum(String... args) {
        for (TestEnum value : TestEnum.values()) {
            System.out.println(String.format(value.getDesc(), args));
        }
    }

    public static String formartDesc(TestEnum value, String... args) {
        return String.format(value.getDesc(), args);
    }
}
