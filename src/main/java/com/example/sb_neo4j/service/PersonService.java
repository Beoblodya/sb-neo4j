package com.example.sb_neo4j.service;


import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.repository.ProjectRepository;
import com.example.sb_neo4j.repository.TaskRepository;
import com.example.sb_neo4j.request.CreatePersonRequestOrDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Optional<Person> getById(Long personId){ return personRepository.findById(personId); }

    public List<Person> getPersonByName(String name){
        return personRepository.findAllByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Person with name <"+name+"> is not found"));
    }

    public List<Person> createPerson(CreatePersonRequestOrDTO request){
        Person person = new Person();
        person.setName(request.getName());
        person.setSkillSet(request.getSkillSet());
        personRepository.save(person);
        return personRepository.findAllByName(request.getName())
                .orElseThrow(() -> new NoSuchElementException("Person is not created"));
    }

    public List<Project> getProjectsByPersonId(Long personId){
        List<Long> projectIds = personRepository.getProjectsByPersonId(personId);
        return projectRepository.findAllById(projectIds);
    }

    public List<Task> getTasksByPersonId(Long personId){
        List<Long> taskIds = personRepository.getTasksByPersonId(personId);
        return taskRepository.findAllById(taskIds);
    }

    public Optional<Person> addSkills(Long personId, List<String> newSkills){
        return personRepository.findById(personRepository.addSkills(personId, newSkills));
    }

    public Optional<Person> deleteASkill(Long personId, String skill){
        return personRepository.findById(personRepository.deleteASkill(personId, skill));
    }

    public Optional<Person> updateSkillSet(Long personId, List<String> newSkills){
        return personRepository.findById(personRepository.updateSkillSet(personId, newSkills));
    }
}
