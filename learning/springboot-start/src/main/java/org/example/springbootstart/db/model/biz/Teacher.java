package org.example.springbootstart.db.model.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable {

    public static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String password;
}
