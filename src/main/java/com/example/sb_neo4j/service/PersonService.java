package com.example.sb_neo4j.service;


import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.repository.ProjectRepository;
import com.example.sb_neo4j.request.CreatePersonRequestOrDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ProjectRepository projectRepository;

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Optional<Person> getById(Long personId){ return personRepository.findById(personId); }

    public Person createPerson(CreatePersonRequestOrDTO request){
        Person person = new Person();
        person.setName(request.getName());
        person.setSkillSet(request.getSkillSet());
        personRepository.save(person);
        return person;
    }

    public List<Project> getProjectsByPersonId(Long personId){
        List<Long> projectIds = personRepository.getProjectsByPersonId(personId);
        return projectRepository.findAllById(projectIds);
    }

    public Optional<Person> addSkills(Long personId, List<String> newSkills){
        return personRepository.findById(personRepository.addSkills(personId, newSkills));
    }
}
