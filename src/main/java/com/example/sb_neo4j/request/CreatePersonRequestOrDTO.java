package com.example.sb_neo4j.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CreatePersonRequestOrDTO {
    private String name;
    private List<String> skillSet;
}
