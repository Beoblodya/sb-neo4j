package com.example.sb_neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ChangeTaskStatusDTO {
    private final Long taskId;
    private final Long issuerId;
}
