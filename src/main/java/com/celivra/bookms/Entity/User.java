package com.celivra.bookms.Entity;

public class User {
    private Long id;
    private String username;
    private String password;
    private int power;

    public User(){}
    public User(String username, String password, int power) {
        this.username = username;
        this.password = password;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPower() {
        return power;
    }

    public Long getId() {
        return id;
    }
}
