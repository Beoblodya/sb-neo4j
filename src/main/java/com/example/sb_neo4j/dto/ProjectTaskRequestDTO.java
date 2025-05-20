package com.example.sb_neo4j.dto;

public class ProjectTaskRequestDTO {
    private Long projectId;
    private Long taskId;

    public ProjectTaskRequestDTO(Long projectId, Long taskId) {
        this.projectId = projectId;
        this.taskId = taskId;
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
}
