package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.model.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends Neo4jRepository<Task, Long> {
    Optional<Task> findTaskByTitle(String title);



    @Query("MATCH (person:Person), (task:Task) WHERE id(person) = $personId AND id(task) = $taskId " +
            "CREATE (person)-[:ASSIGNED]->(task)")
    void assign(Long personId, Long taskId);

    @Query("MATCH (person:Person), (task:Task) WHERE id(person) = $personId AND id(task) = $taskId " +
            "CREATE (person)-[:GENERATED]->(task)")
    void generated(Long personId, Long taskId);
}
