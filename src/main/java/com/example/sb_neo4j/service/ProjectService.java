package com.example.sb_neo4j.service;


import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.repository.ProjectRepository;
import com.example.sb_neo4j.request.CreateProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects(){ return projectRepository.findAll(); }

    public Project createProject(CreateProjectRequest createProjectRequest){
        Project project = new Project();
        project.setTitle(createProjectRequest.getTitle());
        projectRepository.save(project);
        return project;
    }

    public ProjectTaskQueryResult contains(String projectTitle, String taskTitle){
        return projectRepository.contains(projectTitle, taskTitle);
    }

    public ProjectPersonQueryResult member(String projectTitle, String name){
        return projectRepository.member(projectTitle, name);
    }
}
