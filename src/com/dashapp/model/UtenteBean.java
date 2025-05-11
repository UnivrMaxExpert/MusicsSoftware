package com.dashapp.model;

import java.io.Serializable;

public class UtenteBean implements Serializable
{
    private String username, email, password;

    public UtenteBean(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UtenteBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
