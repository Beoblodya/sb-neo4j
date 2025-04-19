package com.example.sb_neo4j.request;

public class CreateTaskRequest {
    private String title;
    private String content;
    private String status;

    public CreateTaskRequest(String title, String content, String status) {
        this.content = content;
        this.status = status;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
