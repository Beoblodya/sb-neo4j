package com.example.sb_neo4j.dto;

public class ProjectPersonResponseDTO {
    private Long projectId;
    private String projectTitle;
    private Long personId;
    private String personName;

    public ProjectPersonResponseDTO(Long projectId, String projectTitle, Long personId, String personName) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.personId = personId;
        this.personName = personName;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
