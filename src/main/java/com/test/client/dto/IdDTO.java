package com.test.client.dto;


import java.io.Serializable;

public class IdDTO implements Serializable{


    private static final long serialVersionUID = -3491514019311523167L;
    private Long id;

    public IdDTO() {
    }

    public IdDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
