package com.example.sb_neo4j.service;


import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.repository.ProjectRepository;
import com.example.sb_neo4j.repository.TaskRepository;
import com.example.sb_neo4j.request.CreateProjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;

    public List<Project> getAllProjects(){ return projectRepository.findAll(); }

    public Optional<Project> getById(Long projectId){ return projectRepository.findById(projectId);}

    public Project createProject(CreateProjectRequest createProjectRequest){
        Project project = new Project();
        project.setTitle(createProjectRequest.getTitle());
        projectRepository.save(project);
        return project;
    }

    public ProjectTaskQueryResult contains(Long projectId, Long taskId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found. Project id: " +projectId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found. Task id: "+taskId));
        projectRepository.contains(projectId, taskId);
        return new ProjectTaskQueryResult(project, task);
    }

    public ProjectPersonQueryResult member(Long projectId, Long personId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found. Project id: "+projectId));
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found. Person id: "+personId));
        projectRepository.member(projectId, personId);
        return new ProjectPersonQueryResult(project, person);
    }

    public List<Task> getProjectTasksPrID (Long projectId){
        List<Long> tasksIds = projectRepository.getProjectTasksPID(projectId);
        return taskRepository.findAllById(tasksIds);
    }

    public List<Person> getProjectPeoplePrID (Long projectId){
        List<Long> peopleIds = projectRepository.getProjectPeoplePID(projectId);
        return personRepository.findAllById(peopleIds);
    }
}
