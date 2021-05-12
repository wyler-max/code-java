package org.example.practicejava.worklab;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjA {
    private int intVal;
    private String stringVal;

    private List<ObjB> objBList;

}
