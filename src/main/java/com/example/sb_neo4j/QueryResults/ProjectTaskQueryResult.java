package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectTaskQueryResult {
    private Project project;
    private Task task;
}
