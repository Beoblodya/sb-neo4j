package com.example.sb_neo4j.dto;

import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
public class ProjectDTO {
    private Project project;
    private List<Person> creator;
    private List<Person> admins;
}
