package com.example.sb_neo4j.request;

public class CreateProjectRequest {
    private String title;

    public CreateProjectRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
