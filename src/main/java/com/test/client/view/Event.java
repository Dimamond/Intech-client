package com.test.client.view;

public class Event {
    private String type;
    private String code;
    private Boolean isSuccess;

    public Event(String type) {
        this.type = type;
    }

    public Event(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public Event(String type, Boolean isSuccess) {
        this.type = type;
        this.isSuccess = isSuccess;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
