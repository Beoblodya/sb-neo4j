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



    @Query("MATCH (project:Project), (task:Task) WHERE project.title = $projectTitle AND task.title = $taskTitle " +
            "CREATE (project)-[:CONTAINS]->(task) RETURN project, task")
    ProjectTaskQueryResult contains(String projectTitle, String taskTitle);

    @Query("MATCH (project:Project), (user:User) WHERE project.title = $projectTitle AND user.name = $name " +
            "CREATE (project)-[:MEMBER]->(user) RETURN project, user")
    ProjectPersonQueryResult member(String projectTitle, String name);
}
