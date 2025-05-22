package com.example.sb_neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonSkillDTO {
    private Long personId;
    private String skill;
}
