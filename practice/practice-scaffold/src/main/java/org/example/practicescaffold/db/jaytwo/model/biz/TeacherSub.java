package org.example.practicescaffold.db.jaytwo.model.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.practicescaffold.db.jaydefault.model.biz.Teacher;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSub extends Teacher {

    public static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String password;
}
