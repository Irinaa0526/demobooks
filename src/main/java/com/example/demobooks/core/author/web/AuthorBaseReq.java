package com.example.demobooks.core.author.web;

import com.example.demobooks.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

public class AuthorBaseReq extends BaseRequest {

    @NotEmpty
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
