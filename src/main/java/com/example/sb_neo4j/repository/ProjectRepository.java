package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.model.Project;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {
    @Query("MATCH (project:Project), (task:Task) WHERE id(project) = $projectId AND id(task) = $taskId " +
            "CREATE (project)-[:CONTAINS]->(task)")
    void contains(Long projectId, Long taskId);

    @Query("MATCH (project:Project), (person:Person) WHERE id(project) = $projectId AND id(person) = $personId " +
            "CREATE (project)-[:CONTAINS]->(person)")
    void member(Long projectId, Long personId);
}
