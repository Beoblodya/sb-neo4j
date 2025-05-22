package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.dto.*;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreateProjectRequest;
import com.example.sb_neo4j.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Project")
public class ProjectController {
    //Контроллер для проектов
    @Autowired
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //Получение всех проектов
    @GetMapping("/getAll")
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    //Получение проекта по id
    @GetMapping("/getById")
    public ResponseEntity<Optional<Project>> getById(@RequestBody ProjectIdDTO request){
        return new ResponseEntity<>(projectService.getById(request.getProjectId()), HttpStatus.OK);
    }

    //Создание проектов команды в базе
    //На вход json с названием
    //На выход json с названием
    @PostMapping("/create")
    public ResponseEntity<CreateProjectRequest> createProject(@RequestBody CreateProjectRequest request){
        Project project = projectService.createProject(request);

        CreateProjectRequest responseProject = new CreateProjectRequest(project.getTitle());

        return new ResponseEntity<>(responseProject, HttpStatus.CREATED);
    }


    //Создание связи между проектом и таском
    //На вход json с названием проекта и названием таска
    //На выход json с названием проекта и названием таска
    @PostMapping("/contains")
    public ResponseEntity<ProjectTaskResponseDTO> contains(@RequestBody ProjectTaskRequestDTO request) throws Exception {
        ProjectTaskQueryResult projectTaskQueryResult = projectService.contains(request.getProjectId(), request.getTaskId());

        ProjectTaskResponseDTO response = new ProjectTaskResponseDTO(projectTaskQueryResult.getProject().getId(),
                projectTaskQueryResult.getProject().getTitle(), projectTaskQueryResult.getTask().getId(), projectTaskQueryResult.getTask().getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Создание связи между проектом и участником команды
    //На вход json с названием проекта и именем
    //На выход json с названием проекта и именем
    @PostMapping("/members")
    public ResponseEntity<ProjectPersonResponseDTO> member(@RequestBody ProjectPersonRequestDTO request){
        ProjectPersonQueryResult projectPersonQueryResult = projectService.member(request.getProjectId(), request.getPersonId());

        ProjectPersonResponseDTO response = new ProjectPersonResponseDTO(projectPersonQueryResult.getProject().getId(), projectPersonQueryResult.getProject().getTitle(),
                projectPersonQueryResult.getPerson().getId(), projectPersonQueryResult.getPerson().getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/project-tasks")
    public ResponseEntity<List<Task>> getProjectTasksPrID(@RequestBody ProjectIdDTO dto){
        return new ResponseEntity<>(projectService.getProjectTasksPrID(dto.getProjectId()), HttpStatus.OK);
    }

    @GetMapping("/project-people")
    public ResponseEntity<List<Person>> getProjectPeoplePrID(@RequestBody ProjectIdDTO dto){
        return new ResponseEntity<>(projectService.getProjectPeoplePrID(dto.getProjectId()), HttpStatus.OK);
    }
}