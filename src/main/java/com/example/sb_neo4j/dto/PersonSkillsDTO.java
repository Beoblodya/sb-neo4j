package com.example.sb_neo4j.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PersonSkillsDTO {
    private Long personId;
    private List<String> personSkillSet;
}
