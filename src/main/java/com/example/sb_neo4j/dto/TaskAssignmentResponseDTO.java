package com.example.sb_neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskAssignmentResponseDTO {
    private Long personId;
    private String personName;
    private Long taskId;
    private String taskTitle;
}
