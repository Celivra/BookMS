package com.celivra.bookms.Entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username, password, phone, email;
    private int power;
}
