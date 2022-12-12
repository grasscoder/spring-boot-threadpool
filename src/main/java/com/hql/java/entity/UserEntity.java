package com.hql.java.entity;

import java.util.StringJoiner;

public class UserEntity {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String sj = new StringJoiner(",")
                .add("{\"id\":" + String.valueOf(id))
                .add("\"username\":" + username)
                .add("\"password\":" + password + "}")
                .toString();
        return sj;
    }
}
