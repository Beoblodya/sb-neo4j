package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectPersonQueryResult {
    private Project project;
    private Person person;
}
