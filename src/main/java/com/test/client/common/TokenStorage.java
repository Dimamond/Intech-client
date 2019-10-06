package com.test.client.common;

public class TokenStorage {

    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TokenStorage.token = token;
    }
}
