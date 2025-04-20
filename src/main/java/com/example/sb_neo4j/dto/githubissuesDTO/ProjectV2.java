package com.example.sb_neo4j.dto.githubissuesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectV2 {
    @JsonProperty("title")
    private String title;

    @JsonProperty("items")
    private Items items;

    public String getTitle() { return title; }
    public Items getItems() { return items; }
}
