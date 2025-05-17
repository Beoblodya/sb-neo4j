package com.example.sb_neo4j.request;

import java.util.List;

public class CreatePersonRequestOrDTO {
    private String name;
    private List<String> skillSet;

    @Deprecated
    public CreatePersonRequestOrDTO(String name) {
        this.name = name;
    }

    public CreatePersonRequestOrDTO(){}

    public CreatePersonRequestOrDTO(String name, List<String> skillSet) {
        this.name = name;
        this.skillSet = skillSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(List<String> skillSet) {
        this.skillSet = skillSet;
    }
}
