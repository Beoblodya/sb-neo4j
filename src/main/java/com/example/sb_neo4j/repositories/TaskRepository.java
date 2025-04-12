package com.example.sb_neo4j.repositories;

import com.example.sb_neo4j.models.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface TaskRepository extends Neo4jRepository<Task, Long> {
    Optional<Task> findTaskbyHeader(String header);
}
