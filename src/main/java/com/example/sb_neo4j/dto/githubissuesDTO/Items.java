package com.example.sb_neo4j.dto.githubissuesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Items {
    @JsonProperty("nodes")
    private List<ItemNode> nodes;

    public List<ItemNode> getNodes() { return nodes; }
    public void setNodes(List<ItemNode> nodes) { this.nodes = nodes; }
}
