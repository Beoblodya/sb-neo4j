package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.QueryResults.PersonRelationQueryResult;
import com.example.sb_neo4j.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    // TODO подумать как вернуть
    @Query("MATCH (p1:Person {name: $name1}) " +
            "MATCH (p2:Person {name: $name2}) " +
            "WHERE NOT (p1)-[:ENROLLED_IN]->(p2) " +
            "CREATE (p1)-[r:ENROLLED_IN]->(p2) " +
            "RETURN p1 as person1, p2 as person2")
    PersonRelationQueryResult SetRelationshipStatus(String name1, String name2);
}
