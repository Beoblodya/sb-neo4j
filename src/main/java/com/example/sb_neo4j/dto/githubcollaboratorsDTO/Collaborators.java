package com.example.sb_neo4j.dto.githubcollaboratorsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Collaborators {
    @JsonProperty("edges")
    List<Edge> edges;

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
