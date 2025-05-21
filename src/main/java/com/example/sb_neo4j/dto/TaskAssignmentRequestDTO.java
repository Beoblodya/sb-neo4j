package com.example.sb_neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskAssignmentRequestDTO {
    private Long personId;
    private Long taskId;
}
