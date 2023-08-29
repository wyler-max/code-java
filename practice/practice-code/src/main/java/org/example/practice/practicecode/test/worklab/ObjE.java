package org.example.practice.practicecode.test.worklab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjE {
    private Byte intVal;
    private String stringVal;

    public static <T> String getVal() {
        return "zzz";
    }
}
