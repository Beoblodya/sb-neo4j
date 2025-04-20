package com.example.sb_neo4j.dto.githubissuesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    @JsonProperty("repository")
    private Repository repository;

    public Repository getRepository() { return repository; }
    public void setRepository(Repository repository) { this.repository = repository; }
}
