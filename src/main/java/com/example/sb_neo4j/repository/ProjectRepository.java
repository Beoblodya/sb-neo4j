package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;
import java.util.List;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

    @Query("MATCH (p:Project) WHERE p.title = $title RETURN p")
    Optional<Project> findProjectByTitle(String title);

    @Query("MATCH (project:Project), (task:Task) WHERE id(project) = $projectId AND id(task) = $taskId " +
            "CREATE (project)-[:CONTAINS]->(task)")
    void contains(Long projectId, Long taskId);

    @Query("MATCH (project:Project), (person:Person) WHERE id(project) = $projectId AND id(person) = $personId " +
            "CREATE (project)-[:MEMBER]->(person)")
    void member(Long projectId, Long personId);

    @Query("MATCH (p:Project)-[r:CONTAINS]->(t:Task) WHERE id(p)=$projectId RETURN id(t)")
    List<Long> getProjectTasksPID (Long projectId);

    @Query("MATCH (p:Project)-[r:MEMBER]->(pe:Person) WHERE id(p)=$projectId RETURN id(pe)")
    List<Long> getProjectPeoplePID (Long projectId);
}
