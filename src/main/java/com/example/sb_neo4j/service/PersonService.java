package com.example.sb_neo4j.service;


import com.example.sb_neo4j.QueryResults.PersonRelationQueryResult;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.request.CreatePersonRequestOrDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Person createPerson(CreatePersonRequestOrDTO request){
        Person person = new Person();
        person.setName(request.getName());
        personRepository.save(person);
        return person;
    }

    public PersonRelationQueryResult setRelation(String name1, String name2){
        return personRepository.SetRelationshipStatus(name1, name2);
    }
}
