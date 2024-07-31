package org.example.practicescaffold.db.springdefault.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSub extends Teacher {

    public static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String password;
}
