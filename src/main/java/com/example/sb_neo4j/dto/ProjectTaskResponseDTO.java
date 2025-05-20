package com.example.sb_neo4j.dto;

public class ProjectTaskResponseDTO {
    private Long projectId;
    private String projectTitle;
    private Long taskId;
    private String taskTitle;

    public ProjectTaskResponseDTO(Long projectId, String projectTitle, Long taskId, String taskTitle) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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