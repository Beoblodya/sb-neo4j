package com.example.sb_neo4j.dto;

public class ProjectPersonRequestDTO {
    private Long projectId;
    private Long personId;

    public ProjectPersonRequestDTO(Long personId, Long projectId) {
        this.personId = personId;
        this.projectId = projectId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
