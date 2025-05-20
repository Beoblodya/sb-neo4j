package com.example.sb_neo4j.dto;

public class TaskAssignmentRequestDTO {
    private Long personId;
    private Long taskId;

    public TaskAssignmentRequestDTO(Long personId, Long taskId) {
        this.personId = personId;
        this.taskId = taskId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
