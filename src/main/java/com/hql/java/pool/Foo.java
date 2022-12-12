package com.hql.java.pool;

public class Foo {
    private String userName;

    private String password;

    public Foo() {
    }

    public Foo(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
