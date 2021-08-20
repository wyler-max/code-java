package org.example.practicescaffold.db.jaytwo.model.biz;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String addr;
}
