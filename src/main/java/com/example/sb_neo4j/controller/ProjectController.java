package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.dto.*;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreateProjectRequest;
import com.example.sb_neo4j.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Project")
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
    @GetMapping("/get-project/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable Long id){
        ProjectDTO projectDTO = new ProjectDTO(projectService.getById(id), projectService.getProjectPeopleByIDAndRole(id, "CREATOR"), projectService.getProjectPeopleByIDAndRole(id, "ADMIN"));
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    //Создание проектов команды в базе
    //На вход json с названием
    //На выход json с названием
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody CreateProjectRequest request){
        Project project = projectService.createProject(request.getCreatorId(), request.getTitle());
        projectService.member(project.getId(), request.getCreatorId());
        projectService.updateRole(project.getId(), request.getCreatorId(), "CREATOR");
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/get-open-tasks-of-project/{id}")
    public ResponseEntity<List<Task>> getOpen(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getOpenTasksOfProject(id), HttpStatus.OK);
    }

    @GetMapping("/get-closed-tasks-of-project/{id}")
    public ResponseEntity<List<Task>> getClosed(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getClosedTasksOfProject(id), HttpStatus.OK);
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

    @PostMapping("/assignAdmin")
    public ResponseEntity<ProjectPersonResponseDTO> assignAdmin(@RequestBody ProjectPersonRequestDTO request){
        ProjectPersonQueryResult projectPersonQueryResult = projectService.updateRole(request.getProjectId(), request.getPersonId(), "ADMIN");

        ProjectPersonResponseDTO response = new ProjectPersonResponseDTO(projectPersonQueryResult.getProject().getId(), projectPersonQueryResult.getProject().getTitle(),
                projectPersonQueryResult.getPerson().getId(), projectPersonQueryResult.getPerson().getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/assignContributor")
    public ResponseEntity<ProjectPersonResponseDTO> assignContributor(@RequestBody ProjectPersonRequestDTO request){
        ProjectPersonQueryResult projectPersonQueryResult = projectService.updateRole(request.getProjectId(), request.getPersonId(), "CONTRIBUTOR");

        ProjectPersonResponseDTO response = new ProjectPersonResponseDTO(projectPersonQueryResult.getProject().getId(), projectPersonQueryResult.getProject().getTitle(),
                projectPersonQueryResult.getPerson().getId(), projectPersonQueryResult.getPerson().getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tasks-of-project/{id}")
    public ResponseEntity<List<Task>> getProjectTasksPrID(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectTasksPrID(id), HttpStatus.OK);
    }

    @GetMapping("/members-of-project/{id}")
    public ResponseEntity<List<Person>> getProjectPeoplePrID(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectPeoplePrID(id), HttpStatus.OK);
    }
    @GetMapping("/creator-of-project/{id}")
    public ResponseEntity<List<Person>> getProjectCreatorById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectPeopleByIDAndRole(id, "CREATOR"), HttpStatus.OK);
    }

    @GetMapping("/admins-of-project/{id}")
    public ResponseEntity<List<Person>> getProjectAdminsById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectPeopleByIDAndRole(id, "ADMIN"), HttpStatus.OK);
    }

    @GetMapping("/contributors-of-project/{id}")
    public ResponseEntity<List<Person>> getProjectContributorsById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectPeopleByIDAndRole(id, "CONTRIBUTOR"), HttpStatus.OK);
    }
}