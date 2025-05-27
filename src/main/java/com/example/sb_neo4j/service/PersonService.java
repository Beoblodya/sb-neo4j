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
import java.util.Objects;
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

    public Person getById(Long personId){ return personRepository.findById(personId)
            .orElseThrow(() -> new NoSuchElementException("Person with id: "+ personId+" is not found")); }

    public List<Person> getPersonByName(String name){
        return personRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Person with name <"+name+"> is not found"));
    }

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

    public List<Task> getTasksByPersonId(Long personId){
        List<Long> taskIds = personRepository.getTasksByPersonId(personId);
        return taskRepository.findAllById(taskIds);
    }

    public Person changeName(Long personId, String newName){
        return personRepository.findById(personRepository.changeName(personId, newName))
                .orElseThrow(() -> new NoSuchElementException("Name was not changed. Person id: "+personId));
    }

    public Person updateSkillSet(Long personId, List<String> newSkills){
        return personRepository.findById(personRepository.updateSkillSet(personId, newSkills))
                .orElseThrow(() -> new NoSuchElementException("Skills were not updated. Person id: "+personId));
    }

    public void deleteSelf(Long personId){
        if (!personRepository.getProjectsByPersonId(personId).isEmpty())
            throw new IllegalStateException("Person-id:"+personId+" is still a part of project");
        personRepository.deletePersonById(personId);
    }

    public void dropTask(Long personId, Long taskId){
        personRepository.dropTask(personId, taskId);
    }

    public void dropFromProject(Long personId, Long projectId, Long issuerId){
        if (projectRepository.personIsCreatorOfProject(personId, projectId)) {
            throw new IllegalStateException("Person-id:"+personId+" cannot be deleted because he is the CREATOR");
        }

        if (!personRepository.isPersonOpInProject(issuerId, projectId) && !issuerId.equals(personId)) {
            throw new IllegalStateException("Deletion of person-id:" + personId + " by person-id:" + issuerId + " is not allowed");
        }

        personRepository.dropFromProject(personId, projectId);
    }
}
