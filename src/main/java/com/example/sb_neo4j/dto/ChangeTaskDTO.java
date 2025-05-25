package com.example.sb_neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangeTaskDTO {
    private final Long taskId;
    private final Long issuerId;
    String newParam;
}
