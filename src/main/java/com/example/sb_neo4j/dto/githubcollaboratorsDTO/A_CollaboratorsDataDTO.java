package com.example.sb_neo4j.dto.githubcollaboratorsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class A_CollaboratorsDataDTO {
    @JsonProperty("data")
    private Data data;

    // Геттеры и сеттеры
    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }
}
