package com.example.sb_neo4j.dto;

import java.util.List;

public class AIResponseDTO {
    private List<TaskAssignmentDTO> assignments;

    public AIResponseDTO() {
    }

    public AIResponseDTO(List<TaskAssignmentDTO> assignments) {
        this.assignments = assignments;
    }

    public List<TaskAssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<TaskAssignmentDTO> assignments) {
        this.assignments = assignments;
    }
}
