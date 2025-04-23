package com.celivra.bookms.Entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private int power;
}
