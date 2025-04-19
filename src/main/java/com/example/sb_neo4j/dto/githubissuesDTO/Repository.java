package com.example.sb_neo4j.dto.githubissuesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Repository {
    @JsonProperty("projectV2")
    private ProjectV2 projectV2;

    public ProjectV2 getProjectV2() { return projectV2; }
    public void setProjectV2(ProjectV2 projectV2) { this.projectV2 = projectV2; }
}
