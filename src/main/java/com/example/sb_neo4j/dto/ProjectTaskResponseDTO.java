package com.example.sb_neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectTaskResponseDTO {
    private Long projectId;
    private String projectTitle;
    private Long taskId;
    private String taskTitle;
}