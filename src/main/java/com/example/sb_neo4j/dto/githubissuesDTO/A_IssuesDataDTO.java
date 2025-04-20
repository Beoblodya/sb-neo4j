package com.example.sb_neo4j.dto.githubissuesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class A_IssuesDataDTO {
    @JsonProperty("data")
    private Data data;

    // Геттеры и сеттеры
    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }
}
