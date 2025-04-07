package com.example.sb_neo4j.repositories;

import com.example.sb_neo4j.QueryResults.PersonRelationQueryResult;
import com.example.sb_neo4j.models.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    @Query("MATCH (person1:Person), (person2:Person) Where person1.name = $name1 AND person2.name = $name2"+
    "CREATE (person1)-[:ENROLLED_IN]->(person2) RETURN person1, person2")
    PersonRelationQueryResult SetRelationshipStatus(String name1, String name2);
}
