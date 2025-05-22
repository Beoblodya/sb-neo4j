package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    @Query("MATCH (p:Project)-[r:MEMBER]->(pe:Person) WHERE id(pe)=$personId RETURN id(p)")
    List<Long> getProjectsByPersonId(Long personId);

    @Query("MATCH (p:person)-[:ASSIGNED]->(t:task) WHERE id(p) = $personId RETURN id(t)")
    List<Long> getTasksByPersonId(Long personId);

    @Query("MATCH (pe:Person) WHERE id(pe)=$personId SET pe.skillSet = coalesce(pe.skillSet, []) + $newSkills RETURN id(pe)")
    Long addSkills(Long personId, List<String> newSkills);

    @Query("MATCH (pe:Person) WHERE id(pe)=$personId SET pe.skillSet =  [x IN pe.skillSet WHERE x <> $skill] RETURN id(pe)")
    Long deleteASkill(Long personId, String skill);

    @Query("MATCH (pe:Person) WHERE id(pe)=$personId SET pe.skillSet = $newSkills RETURN id(pe)")
    Long updateSkillSet(Long personId, List<String> newSkills);
}
