package com.example.sb_neo4j.dto;

public class TaskAssignmentDTO {
    private String name;
    private String title;

    public TaskAssignmentDTO(String name, String title) {
        this.name = name;
        this.title = title;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
