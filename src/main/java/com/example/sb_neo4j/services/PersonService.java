package com.example.sb_neo4j.services;


import com.example.sb_neo4j.QueryResults.PersonRelationQueryResult;
import com.example.sb_neo4j.models.Person;
import com.example.sb_neo4j.repositories.PersonRepository;
import com.example.sb_neo4j.requests.CreatePersonRequestOrDTO;
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
