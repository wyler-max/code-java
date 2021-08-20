package org.example.practicescaffold.db.jaytwo.model.biz;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    public static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String password;
}
