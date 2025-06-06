package com.example.sb_neo4j.service;


import com.example.sb_neo4j.QueryResults.ProjectPersonQueryResult;
import com.example.sb_neo4j.QueryResults.ProjectTaskQueryResult;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.repository.ProjectRepository;
import com.example.sb_neo4j.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;

    public List<Project> getAllProjects(){ return projectRepository.findAll(); }

    public Project getById(Long projectId){ return projectRepository.findById(projectId)
            .orElseThrow(() -> new NoSuchElementException("Project with id: "+projectId+" is not found"));}

    public List<Task> getOpenTasksOfProject(Long projectId){
        List<Long> tasksIds = projectRepository.getOpenTasksOfProject(projectId)
                .orElseThrow(() -> new NoSuchElementException("No opened tasks in project-:id"+projectId));
        return taskRepository.findAllById(tasksIds);
    }

    public List<Task> getClosedTasksOfProject(Long projectId){
        List<Long> tasksIds =  projectRepository.getClosedTasksOfProject(projectId)
                .orElseThrow(() -> new NoSuchElementException("No closed tasks in project-:id"+projectId));
        return taskRepository.findAllById(tasksIds);
    }

    public List<Task> getInProgressTasksOfProject(Long projectId){
        List<Long> tasksIds =  projectRepository.getInProgressTasksOfProject(projectId)
                .orElseThrow(() -> new NoSuchElementException("No closed tasks in project-:id"+projectId));
        return taskRepository.findAllById(tasksIds);
    }

    public List<Project> getProjectsByPersonId(Long personId){
        List<Long> projectIds = personRepository.getProjectsByPersonId(personId);
        return projectRepository.findAllById(projectIds);
    }

    public Project createProject(Long creatorId, String title){
        if (projectRepository.personIsCreatorOfProject(creatorId,title))
            throw new IllegalStateException("Project '"+title+"' is already created by person-id:"+creatorId);
        Project project = new Project();
        project.setTitle(title);
        projectRepository.save(project);
        return project;
    }

    public ProjectPersonQueryResult updateRole(Long projectId, Long personId, String role){
        if (projectRepository.getRole(personId, projectId).equals("CREATOR"))
            throw new IllegalStateException("'CREATOR' role cannot be reassigned");
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found. Project id: "+projectId));
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found. Person id: "+personId));
        projectRepository.updateRole(projectId, personId, role);
        return new ProjectPersonQueryResult(project, person);
    }

    public ProjectTaskQueryResult contains(Long projectId, Long taskId){
        if (projectRepository.areRelated(projectId, taskId))
            throw new IllegalStateException("Task-id:"+taskId+ " is already bound to project-id:"+projectId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found. Project id: " +projectId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found. Task id: "+taskId));
        projectRepository.contains(projectId, taskId);
        return new ProjectTaskQueryResult(project, task);
    }

    public ProjectPersonQueryResult member(Long projectId, Long personId){
        if (projectRepository.areRelated(projectId, personId))
            throw new IllegalStateException("Person-id:"+personId+ " is already a contributor of project-id:"+projectId);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found. Project id: "+projectId));
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found. Person id: "+personId));
        projectRepository.member(projectId, personId);
        return new ProjectPersonQueryResult(project, person);
    }

    public List<Task> getProjectTasksPrID (Long projectId){
        List<Long> tasksIds = projectRepository.getProjectTasksPID(projectId);
        return taskRepository.findAllById(tasksIds);
    }

    public List<Person> getProjectPeoplePrID (Long projectId){
        List<Long> peopleIds = projectRepository.getProjectPeoplePID(projectId);
        return personRepository.findAllById(peopleIds);
    }

    public List<Person> getProjectPeopleByIDAndRole(Long projectId, String role){
        List<Long> peopleIds = projectRepository.getProjectPeopleByIDAndRole(projectId, role);
        return personRepository.findAllById(peopleIds);
    }

    public Project changeTitle(Long projectId, String newTitle){
        return projectRepository.findById(projectRepository.changeTitle(projectId, newTitle))
                .orElseThrow(() -> new NoSuchElementException("Title was not changed. Person id: "+projectId));
    }

    public boolean deleteProjectById(Long projectId){
        projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project-id:"+projectId+" is not found"));
        List<Long> tasksIds = projectRepository.getProjectTasksPID(projectId);
        for (Long taskId : tasksIds){
            taskRepository.deleteTaskById(taskId);
        }
        projectRepository.deleteProjectById(projectId);
        return true;
    }
}
