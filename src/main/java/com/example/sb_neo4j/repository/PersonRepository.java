package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

}
