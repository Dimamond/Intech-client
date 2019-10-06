package com.test.client.exception;


public class MyServerException extends RuntimeException {

    private int statusCode;

    public MyServerException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public MyServerException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }


    public MyServerException(String message) {
        super(message);
    }

    public MyServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
