package com.example.sb_neo4j.dto;

public class ProjectPersonDTO {
    private String projectTitle;
    private String name;

    public ProjectPersonDTO(String name, String projectTitle) {
        this.name = name;
        this.projectTitle = projectTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
}
