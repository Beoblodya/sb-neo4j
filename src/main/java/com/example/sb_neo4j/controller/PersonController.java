package com.example.sb_neo4j.controller;

import com.example.sb_neo4j.dto.*;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreatePersonRequestOrDTO;
import com.example.sb_neo4j.service.PersonService;
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
    @GetMapping("/getById")
    public ResponseEntity<Optional<Person>> getById(@RequestBody PersonIdDTO request){
        return new ResponseEntity<>(personService.getById(request.getPersonId()), HttpStatus.OK);
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
    @GetMapping("/person-project")
    public ResponseEntity<List<Project>> getProjectsByPersonId(@RequestBody PersonIdDTO request){
        return new ResponseEntity<>(personService.getProjectsByPersonId(request.getPersonId()), HttpStatus.OK);
    }

    //Получение тасков по id участника команды
    @GetMapping("/person-task")
    public ResponseEntity<List<Task>> getTasksByPersonId(@RequestBody PersonIdDTO request){
        return new ResponseEntity<>(personService.getTasksByPersonId(request.getPersonId()), HttpStatus.OK);
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

    @GetMapping("/getByName")
    public ResponseEntity<List<Person>> getPersonByName(@RequestBody PersonNameDTO dto){
        return new ResponseEntity<>(personService.getPersonByName(dto.getName()), HttpStatus.OK);
    }
}
