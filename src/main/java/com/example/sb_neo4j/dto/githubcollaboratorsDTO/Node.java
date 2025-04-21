package com.example.sb_neo4j.dto.githubcollaboratorsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Node {
    @JsonProperty("login")
    String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
