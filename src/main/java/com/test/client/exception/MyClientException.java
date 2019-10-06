package com.test.client.exception;


import org.apache.http.HttpStatus;

public class MyClientException extends RuntimeException {


    public MyClientException(String message) {
        super(message);
    }

    public MyClientException(String message, Throwable cause) {
        super(message, cause);
    }


}
