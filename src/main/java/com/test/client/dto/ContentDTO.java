package com.test.client.dto;




import java.io.Serializable;

public class ContentDTO implements Serializable {


    private static final long serialVersionUID = -2809022323693404824L;
    protected Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
