package com.example.sb_neo4j.service;


import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.repository.TaskRepository;
import com.example.sb_neo4j.request.CreateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final PersonRepository personRepository;
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks (){
        return taskRepository.findAll();
    }

    public Task getById(Long taskId){ return taskRepository.findById(taskId)
            .orElseThrow(() -> new NoSuchElementException("Task with id "+taskId+" is not found")); }

    public Task createTask(CreateTaskRequest createTaskRequest){
        Task task = new Task();
        task.setContent(createTaskRequest.getContent());
        task.setTitle(createTaskRequest.getTitle());
        task.setStatus("open");
        taskRepository.save(task);
        return task;
    }

    public TaskQueryResult assign(Long personId, Long taskId){
        if (taskRepository.areRelated(personId, taskId))
            throw new IllegalStateException("Task-id:"+taskId+ " is already assigned to person-id:"+personId);
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found. Person id: "+personId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found. Task id: "+taskId));
        taskRepository.assign(personId, taskId);
        return new TaskQueryResult(task, person);
    }

    public TaskQueryResult generated(Long personId, Long taskId){
        if (taskRepository.areRelated(personId, taskId))
            throw new IllegalStateException("Task-id:"+taskId+ " is already assigned to person-id:"+personId);
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found. Person id: "+personId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found. Task id: "+taskId));
        taskRepository.generated(personId, taskId);
        return new TaskQueryResult(task, person);
    }


    public boolean closeTask(Long taskId, Long issuerId){
        if (!taskRepository.areRelated(taskId, issuerId))
            if (!personRepository.isPersonOpInProject(issuerId, taskRepository.getProjectByTaskId(taskId)))
                return false;
        taskRepository.closeTask(taskId);
        return true;
    }

    public boolean openTask(Long taskId, Long issuerId){
        if (!taskRepository.areRelated(taskId, issuerId))
            if (!personRepository.isPersonOpInProject(issuerId, taskRepository.getProjectByTaskId(taskId)))
                return false;
        taskRepository.openTask(taskId);
        return true;
    }

    public boolean changeTaskTitle(Long taskId, Long issuerId, String title){
        if (!taskRepository.areRelated(taskId, issuerId))
            if (!personRepository.isPersonOpInProject(issuerId, taskRepository.getProjectByTaskId(taskId)))
                return false;
        taskRepository.changeTaskTitle(taskId, title);
        return true;
    }

    public boolean changeTaskContent(Long taskId, Long issuerId, String content){
        if (!taskRepository.areRelated(taskId, issuerId))
            if (!personRepository.isPersonOpInProject(issuerId, taskRepository.getProjectByTaskId(taskId)))
                return false;
        taskRepository.changeTaskContent(taskId, content);
        return true;
    }

    public List<Person> getPersonByTaskId(Long taskId){
        List<Long> personIds = taskRepository.getPersonByTaskId(taskId);
        return personRepository.findAllById(personIds);
    }

    public boolean deleteTaskById(Long taskId, Long issuerId){
            if (!personRepository.isPersonOpInProject(issuerId, taskRepository.getProjectByTaskId(taskId)))
                if (!taskRepository.areRelated(taskId, issuerId))
                    return false;
        taskRepository.deleteTaskById(taskId);
        return true;
    }


}
