package com.example.sb_neo4j.dto;

public record TaskDTO(
        String title,
        String description,
        String status
        //TODO подвязать enum
) {
}
