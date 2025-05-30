package com.example.sb_neo4j.controller;

import com.example.sb_neo4j.dto.*;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreatePersonRequestOrDTO;
import com.example.sb_neo4j.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Person")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/Person")
public class PersonController {
    //Контроллер для участников команды
    private final PersonService personService;

    @GetMapping("/getAll")
    @Operation(summary = "Получение всех пользователей из базы")
    public ResponseEntity<List<Person>> personIndex(){
        List<Person> people = personService.getAllPerson();
        return new ResponseEntity<>(people,
                people.isEmpty()?
                    HttpStatus.NO_CONTENT : HttpStatus.FOUND);
    }

    //Получение участника команды по id
    @GetMapping("/get-person/{id}")
    @Operation(summary = "Получение участника команды по id")
    public ResponseEntity<Person> getById(@PathVariable Long id){
        return new ResponseEntity<>(personService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/projects-of-person/{id}")
    @Operation(summary = "Поучение проектов по id участника команды")
    public ResponseEntity<List<Project>> getProjectsByPersonId(@PathVariable Long id){
        List<Project> projects = personService.getProjectsByPersonId(id);
        return new ResponseEntity<>(projects,
                projects.isEmpty()?
                        HttpStatus.NO_CONTENT : HttpStatus.FOUND);
    }

    @GetMapping("/tasks-of-person/{id}")
    @Operation(summary = "Получение тасков по id участника команды")
    public ResponseEntity<List<Task>> getTasksByPersonId(@PathVariable Long id){
        List<Task> tasks = personService.getTasksByPersonId(id);
        return new ResponseEntity<>(tasks,
                tasks.isEmpty()?
                        HttpStatus.NO_CONTENT : HttpStatus.FOUND);
    }

    @GetMapping("/get-person-by-name/{name}")
    @Operation(summary = "Получение пользователя по имени")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable String name){
        return new ResponseEntity<>(personService.getPersonByName(name), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "Создание пользователя")
    public ResponseEntity<Person> personCreate(@RequestBody CreatePersonRequestOrDTO request){
        Person person = personService.createPerson(request);

        return new ResponseEntity<>(personService.getById(person.getId()), HttpStatus.CREATED);
    }

    @PutMapping("/changeName")
    @Operation(summary = "Изменение имени пользователя")
    public ResponseEntity<Person> changeName(@RequestBody PersonNewNameDTO request){
        return new ResponseEntity<>(personService.changeName(request.getPersonId(), request.getNewName()), HttpStatus.OK);
    }

    //Обновление скиллов(замена массива)
    @PutMapping("/updateSkillSetById")
    @Operation(summary = "Изменение скилов пользователя")
    public ResponseEntity<Person> updateSkillSet(@RequestBody PersonSkillsDTO request){
        return new ResponseEntity<>(personService.updateSkillSet(request.getPersonId(), request.getPersonSkillSet()), HttpStatus.OK);
    }

    @Operation(summary = "Удаление задачи пользователя из базы (не использовать, замена в TaskController)")
    @DeleteMapping("/dropTask")
    public ResponseEntity<TaskAssignmentRequestDTO> dropTask(@RequestBody TaskAssignmentRequestDTO requestDTO){
        personService.dropTask(requestDTO.getPersonId(), requestDTO.getTaskId());
        return new ResponseEntity<>(requestDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSelf/{personId}")
    @Operation(summary = "Удаление пользователя из базы")
    public ResponseEntity<String> deleteSelf(@PathVariable Long personId){
        personService.deleteSelf(personId);
        return new ResponseEntity<>("Person-id:"+personId+" has been deleted from database", HttpStatus.OK);    }

    @PutMapping("/leave-project")
    @Operation(summary = "Удаление пользователя из проекта")
    public ResponseEntity<ProjectPersonRequestDTO> dropPersonFromProject(@RequestBody ProjectPersonRequestDTO dto){
        personService.dropFromProject(dto.getPersonId(), dto.getProjectId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
