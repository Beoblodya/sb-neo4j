package com.example.sb_neo4j.dto;

import java.util.List;

public record TaskDTO(
        Long id,
        String title,
        String description,
        List<String> skillsRequired,
        String status
        //TODO подвязать enum
) {
}
