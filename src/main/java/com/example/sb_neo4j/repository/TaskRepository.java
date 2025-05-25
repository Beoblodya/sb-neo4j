package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.model.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends Neo4jRepository<Task, Long> {
    Optional<Task> findTaskByTitle(String title);

    @Query("MATCH (t:Task) WHERE id(t)=$taskId SET t.status='closed'")
    void closeTask(Long taskId);

    @Query("MATCH (t:Task) WHERE id(t)=$taskId SET t.status='open'")
    void openTask(Long taskId);

    @Query("MATCH (person:Person), (task:Task) WHERE id(person) = $personId AND id(task) = $taskId " +
            "CREATE (person)-[:ASSIGNED]->(task)")
    void assign(Long personId, Long taskId);

    @Query("MATCH (person:Person), (task:Task) WHERE id(person) = $personId AND id(task) = $taskId " +
            "CREATE (person)-[:GENERATED]->(task)")
    void generated(Long personId, Long taskId);

    @Query("MATCH (p:Person)-[:ASSIGNED]->(t:Task) WHERE id(t) = $taskId RETURN id(p)")
    List<Long> getPersonByTaskId(Long taskId);

    @Query("MATCH (t:Task)-[r:CONTAINS]-(p:Project) WHERE id(t)=$taskId RETURN id(p)")
    Long getProjectByTaskId(Long taskId);

    @Query("RETURN EXISTS {MATCH (a)-[r]-(b) WHERE id(a) = $id1 AND id(b) = $id2}")
    boolean areRelated(Long id1, Long id2);

    @Query("MATCH (t:Task) WHERE id(t)=$taskId DETACH DELETE t")
    void deleteTaskById(Long taskId);

    @Query("MATCH (t:Task) WHERE id(t)=$taskId SET t.title=$title")
    void changeTaskTitle(Long taskId, String title);

    @Query("MATCH (t:Task) WHERE id(t)=$taskId SET t.content=$content")
    void changeTaskContent(Long taskId, String content);
}
