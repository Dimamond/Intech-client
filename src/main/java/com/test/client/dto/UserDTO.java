package com.test.client.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {


    private static final long serialVersionUID = 959455169655783336L;
    private Long id;

    private String login;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
