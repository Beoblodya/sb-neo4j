package com.example.sb_neo4j.controllers;

import com.example.sb_neo4j.QueryResults.PersonRelationQueryResult;
import com.example.sb_neo4j.models.Person;
import com.example.sb_neo4j.requests.CreatePersonRequestOrDTO;
import com.example.sb_neo4j.requests.CreateRelationDTO;
import com.example.sb_neo4j.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Person>> personIndex(){
        return new ResponseEntity<>(personService.getAllPerson(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreatePersonRequestOrDTO> personCreate(@RequestBody CreatePersonRequestOrDTO request){
        Person person = personService.createPerson(request);

        CreatePersonRequestOrDTO responsePerson = new CreatePersonRequestOrDTO(person.getName());

        return new ResponseEntity<>(responsePerson, HttpStatus.CREATED);
    }

    @PostMapping("/relation")
    public ResponseEntity<CreateRelationDTO> setRelation(@RequestBody CreateRelationDTO request){
        PersonRelationQueryResult personRelationQueryResult = personService.setRelation(request.getName1(), request.getName1());

        CreateRelationDTO responseRelation = new CreateRelationDTO(personRelationQueryResult.getPerson1().getName(), personRelationQueryResult.getPerson2().getName());
        return new ResponseEntity<>(responseRelation, HttpStatus.OK);
    }
}
