package com.test.client.dto;


import java.io.Serializable;

public class AuthorizationDTO implements Serializable {


    private static final long serialVersionUID = 5193523042329057561L;
    private String login;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
