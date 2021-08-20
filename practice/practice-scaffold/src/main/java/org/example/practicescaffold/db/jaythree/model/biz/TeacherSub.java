package org.example.practicescaffold.db.jaythree.model.biz;

import org.example.practicescaffold.db.jaydefault.model.biz.Teacher;

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
