package org.example.springbootstart.dtos.param.user;

import lombok.Data;

@Data
public class UserReq {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String phone;
}
