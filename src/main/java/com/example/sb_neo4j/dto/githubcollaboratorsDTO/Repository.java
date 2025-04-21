package com.example.sb_neo4j.dto.githubcollaboratorsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Repository {
    @JsonProperty("collaborators")
    Collaborators collaborators;

    public Collaborators getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(Collaborators collaborators) {
        this.collaborators = collaborators;
    }
}
