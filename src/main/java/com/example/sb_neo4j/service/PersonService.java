package com.example.sb_neo4j.service;


import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.request.CreatePersonRequestOrDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Person createPerson(CreatePersonRequestOrDTO request){
        Person person = new Person();
        person.setName(request.getName());
        person.setSkillSet(request.getSkillSet());
        personRepository.save(person);
        return person;
    }
}
