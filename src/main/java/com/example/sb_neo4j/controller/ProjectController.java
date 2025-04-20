package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.dto.ProjectPersonDTO;
import com.example.sb_neo4j.dto.ProjectTaskDTO;
import com.example.sb_neo4j.dto.TaskAssignmentDTO;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreateProjectRequest;
import com.example.sb_neo4j.request.CreateTaskRequest;
import com.example.sb_neo4j.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Project")
public class ProjectController {
    @Autowired
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateProjectRequest> createProject(@RequestBody CreateProjectRequest request){
        Project project = projectService.createProject(request);

        CreateProjectRequest responseProject = new CreateProjectRequest(project.getTitle());

        return new ResponseEntity<>(responseProject, HttpStatus.CREATED);
        //TODO добавлять из git
    }

    @PostMapping("/contains")
    public ResponseEntity<ProjectTaskDTO> contains(@RequestBody ProjectTaskDTO request){
        ProjectTaskQueryResult projectTaskQueryResult = projectService.contains(request.getProjectTitle(), request.getTaskTitle());

        ProjectTaskDTO response = new ProjectTaskDTO(projectTaskQueryResult.getProject().getTitle(), projectTaskQueryResult.getTask().getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<ProjectPersonDTO> member(@RequestBody ProjectPersonDTO request){
        ProjectPersonQueryResult projectPersonQueryResult = projectService.member(request.getProjectTitle(), request.getName());

        ProjectPersonDTO response = new ProjectPersonDTO(projectPersonQueryResult.getProject().getTitle(), projectPersonQueryResult.getPerson().getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
