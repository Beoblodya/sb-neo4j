package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, Long> {

}
