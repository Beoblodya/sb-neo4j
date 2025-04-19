package com.example.sb_neo4j.dto.githubissuesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemNode {
    @JsonProperty("content")
    private Content content;

    public Content getContent() { return content; }
    public void setContent(Content content) { this.content = content; }
}