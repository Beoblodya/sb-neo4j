package com.example.sb_neo4j.dto;

public class TaskAssignmentResponseDTO {
    private Long personId;
    private String personName;
    private Long taskId;
    private String taskTitle;

    public TaskAssignmentResponseDTO(Long personId, String personName, Long taskId, String taskTitle) {
        this.personId = personId;
        this.personName = personName;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
}
