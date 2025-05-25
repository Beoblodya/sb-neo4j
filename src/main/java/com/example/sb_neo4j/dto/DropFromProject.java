package com.example.sb_neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DropFromProject {
    private final Long projectId;
    private final Long nodeId;
    private final Long issuerId;
}
