package com.example.sb_neo4j.dto.githubcollaboratorsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Edges {
    @JsonProperty("edges")
    List<EdgeItem> edgeItems;

    public List<EdgeItem> getEdgeItems() {
        return edgeItems;
    }

    public void setEdgeItems(List<EdgeItem> edgeItems) {
        this.edgeItems = edgeItems;
    }
}
