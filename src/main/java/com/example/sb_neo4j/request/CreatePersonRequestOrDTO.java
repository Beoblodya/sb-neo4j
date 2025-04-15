package com.example.sb_neo4j.request;

public class CreatePersonRequestOrDTO {
    private String name;

    public CreatePersonRequestOrDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
