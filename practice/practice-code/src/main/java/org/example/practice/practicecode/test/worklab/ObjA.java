package org.example.practice.practicecode.test.worklab;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjA {
    private int intVal;
    private String stringVal;

    private List<ObjB> objBList;

}
