package org.example.practicescaffold.db.springdefault.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String addr;
}
