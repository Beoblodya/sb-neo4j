package com.example.sb_neo4j.dto;

public class ProjectTaskDTO {
    private String projectTitle;
    private String taskTitle;

    public ProjectTaskDTO(String projectTitle, String taskTitle) {
        this.projectTitle = projectTitle;
        this.taskTitle = taskTitle;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
}
