package com.example.sb_neo4j.controller;

import com.example.sb_neo4j.dto.*;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreatePersonRequestOrDTO;
import com.example.sb_neo4j.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Person")
public class PersonController {
    //Контроллер для участников команды
    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;;
    }

    //Получение всех участников команды из базы
    @GetMapping("/getAll")
    public ResponseEntity<List<Person>> personIndex(){
        return new ResponseEntity<>(personService.getAllPerson(), HttpStatus.OK);
    }

    //Получение участника команды по id
    @GetMapping("/get-person/{id}")
    public ResponseEntity<Optional<Person>> getById(@PathVariable Long id){
        return new ResponseEntity<>(personService.getById(id), HttpStatus.OK);
    }

    //Создание участника команды в базе
    //На вход json с именем
    //На выход json с именем
    @PostMapping("/create")
    public ResponseEntity<Optional<Person>> personCreate(@RequestBody CreatePersonRequestOrDTO request){
        Person person = personService.createPerson(request);

        return new ResponseEntity<>(personService.getById(person.getId()), HttpStatus.CREATED);
    }

    //Поучение проектов по id участника команды
    @GetMapping("/projects-of-person/{id}")
    public ResponseEntity<List<Project>> getProjectsByPersonId(@PathVariable Long id){
        return new ResponseEntity<>(personService.getProjectsByPersonId(id), HttpStatus.OK);
    }

    //Получение тасков по id участника команды
    @GetMapping("/tasks-of-person/{id}")
    public ResponseEntity<List<Task>> getTasksByPersonId(@PathVariable Long id){
        return new ResponseEntity<>(personService.getTasksByPersonId(id), HttpStatus.OK);
    }

    //Добавление скиллов
    @PostMapping("/addSkillsById")
    public ResponseEntity<Optional<Person>> addSkills(@RequestBody PersonSkillsDTO request){
        return new ResponseEntity<>(personService.addSkills(request.getPersonId(), request.getPersonSkillSet()), HttpStatus.OK);
    }

    //Удаление скилла
    @PostMapping("/deleteASkillById")
    public ResponseEntity<Optional<Person>> deleteASkill(@RequestBody PersonSkillDTO request){
        return new ResponseEntity<>(personService.deleteASkill(request.getPersonId(), request.getSkill()), HttpStatus.OK);
    }

    //Обновление скиллов(замена массива)
    @PostMapping("/updateSkillSetById")
    public ResponseEntity<Optional<Person>> updateSkillSet(@RequestBody PersonSkillsDTO request){
        return new ResponseEntity<>(personService.updateSkillSet(request.getPersonId(), request.getPersonSkillSet()), HttpStatus.OK);
    }

    @PostMapping("/get-person-by-name/{name}")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable String name){
        return new ResponseEntity<>(personService.getPersonByName(name), HttpStatus.OK);
    }
}
