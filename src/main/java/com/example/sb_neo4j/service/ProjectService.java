package com.example.sb_neo4j.service;


import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.repository.ProjectRepository;
import com.example.sb_neo4j.repository.TaskRepository;
import com.example.sb_neo4j.request.CreateProjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;

    public List<Project> getAllProjects(){ return projectRepository.findAll(); }

    public Project createProject(CreateProjectRequest createProjectRequest){
        Project project = new Project();
        project.setTitle(createProjectRequest.getTitle());
        projectRepository.save(project);
        return project;
    }

    public ProjectTaskQueryResult contains(Long projectId, Long taskId) throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("Project not found"));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found"));
        projectRepository.contains(projectId, taskId);
        return new ProjectTaskQueryResult(project, task);
    }

    public ProjectPersonQueryResult member(Long projectId, Long personId){
        return projectRepository.member(projectId, personId);
    }
}
