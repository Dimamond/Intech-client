package com.test.client.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {


    private static final long serialVersionUID = 7969516185442714170L;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}