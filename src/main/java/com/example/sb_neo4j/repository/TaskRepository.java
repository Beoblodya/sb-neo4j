package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.model.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface TaskRepository extends Neo4jRepository<Task, Long> {
    Optional<Task> findTaskByTitle(String title);
}
