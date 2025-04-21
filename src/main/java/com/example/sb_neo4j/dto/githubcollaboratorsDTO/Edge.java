package com.example.sb_neo4j.dto.githubcollaboratorsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Edge {
    @JsonProperty("node")
    Node node;
    @JsonProperty("permission")
    String permission;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
