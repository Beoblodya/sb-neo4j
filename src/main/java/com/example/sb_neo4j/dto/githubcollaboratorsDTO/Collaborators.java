package com.example.sb_neo4j.dto.githubcollaboratorsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Collaborators {
    @JsonProperty("collaborators")
    Edges edges;

    public Edges getEdges() {
        return edges;
    }

    public void setEdges(Edges edges) {
        this.edges = edges;
    }
}
