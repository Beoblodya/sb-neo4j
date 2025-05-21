package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskQueryResult {
    private Task task;
    private Person person;
}
