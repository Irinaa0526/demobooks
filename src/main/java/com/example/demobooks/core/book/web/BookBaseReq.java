package com.example.demobooks.core.book.web;

import com.example.demobooks.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BookBaseReq extends BaseRequest {

    //@NotEmpty
    private String title;

    private String description;

    //@NotEmpty
    private List<@Valid Id> authors;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Id> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Id> authors) {
        this.authors = authors;
    }
}
