package com.example.sb_neo4j.repositories;

import com.example.sb_neo4j.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
    Optional<User> findUserbyName(String name);
}
