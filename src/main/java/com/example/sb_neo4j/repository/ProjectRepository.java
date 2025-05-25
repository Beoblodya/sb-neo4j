package com.example.sb_neo4j.repository;

import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;
import java.util.List;

public interface ProjectRepository extends Neo4jRepository<Project, Long> {

    @Query("MATCH (t:Task)-[r:CONTAINS]-(p:Project) WHERE id(p)=$projectId AND t.status = 'open' RETURN id(t)")
    Optional<List<Long>> getOpenTasksOfProject(Long projectId);

    @Query("MATCH (t:Task)-[r:CONTAINS]-(p:Project) WHERE id(p)=$projectId AND t.status = 'closed' RETURN id(t)")
    Optional<List<Long>> getClosedTasksOfProject(Long projectId);

    @Query("MATCH (project:Project), (task:Task) WHERE id(project) = $projectId AND id(task) = $taskId " +
            "CREATE (project)-[:CONTAINS]->(task)")
    void contains(Long projectId, Long taskId);

    @Query("MATCH (project:Project), (person:Person) WHERE id(project) = $projectId AND id(person) = $personId " +
            "CREATE (project)-[:MEMBER {role: 'CONTRIBUTOR'}]->(person)")
    void member(Long projectId, Long personId);

    @Query("MATCH (pr:Project)-[r:MEMBER]-(pe:Person) WHERE id(pr) = $projectId AND id(pe) = $personId " +
            "SET r.role = $role")
    void updateRole(Long projectId, Long personId, String role);

    @Query("MATCH (p:Project)-[r:CONTAINS]->(t:Task) WHERE id(p)=$projectId RETURN id(t)")
    List<Long> getProjectTasksPID (Long projectId);

    @Query("MATCH (p:Project)-[r:MEMBER]->(pe:Person) WHERE id(p)=$projectId RETURN id(pe)")
    List<Long> getProjectPeoplePID (Long projectId);

    @Query("MATCH (p:Project)-[r:MEMBER]->(pe:Person) WHERE id(p)=$projectId AND r.role = $role RETURN id(pe)")
    List<Long> getProjectPeopleByIDAndRole (Long projectId, String role);

    @Query("RETURN EXISTS {MATCH (a)-[r]-(b) WHERE id(a) = $id1 AND id(b) = $id2}")
    boolean areRelated(Long id1, Long id2);

    @Query("RETURN EXISTS {MATCH (pe:Person)-[r:MEMBER]-(pr:Project) WHERE id(pe)=$personId AND pr.title=$projectTitle AND r.role='CREATOR'}")
    boolean personIsCreatorOfProject(Long personId, String projectTitle);

    @Query("MATCH (pe:Person)-[r]-(pr:Project) WHERE id(pe)=$personId AND id(pr)=$projectId RETURN r.role LIMIT 1")
    String getRole(Long personId, Long projectId);

    @Query("MATCH (p:Project) WHERE id(project) = $projectId SET project.title = $newTitle RETURN id(project)")
    Long changeTitle(Long projectId, String newTitle);

    @Query("MATCH (p:Project) WHERE id(project) = $projectId DETACH DELETE project")
    void deleteProjectById(Long projectId);
}
