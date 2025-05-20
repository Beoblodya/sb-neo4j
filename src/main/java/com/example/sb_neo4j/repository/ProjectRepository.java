package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.model.Project;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {
    Optional<Project> findProjectByTitle(String title);



    @Query("MATCH (project:Project), (task:Task) WHERE project.id = $projectId AND task.id = $taskId " +
            "CREATE (project)-[:CONTAINS]->(task) RETURN project, task")
    ProjectTaskQueryResult contains(Long projectId, Long taskId);

    @Query("MATCH (project:Project), (person:Person) WHERE project.id = $projectId AND person.id = $personId " +
            "CREATE (project)-[:MEMBER]->(person) RETURN project, person")
    ProjectPersonQueryResult member(Long projectId, Long personId);
}
