package com.example.sb_neo4j.repositories;

import com.example.sb_neo4j.QueryResults.TaskAssignmentQueryResult;
import com.example.sb_neo4j.models.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends Neo4jRepository<Task, Long> {
    Optional<Task> findTaskbyHeader(String header);

    @Query("MATCH (:User {name: $name}) -[:ASSIGNED]->(tasks:Task) RETURN tasks")
    List<Task> findAllTasksofUser(String name);

    @Query("MATCH (task:Task), (user:User) WHERE task.title = $title AND user.name = $name " +
            "RETURN EXISTS((task)-[ASSIGNED]->(user))")
    Boolean findAssignmentStatus(String title, String name);

    @Query("MATCH (task:Task), (user:User) WHERE task.title = $title AND user.name = $name " +
            "CREATE (task)-[ASSIGNED]->(user) RETURN task, user")
    TaskAssignmentQueryResult assignTasktoUser(String title, String name);
}
