package com.example.sb_neo4j.service;


import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.repository.TaskRepository;
import com.example.sb_neo4j.request.CreateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    @Autowired
    private final PersonRepository personRepository;
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks (){
        return taskRepository.findAll();
    }

    public Optional<Task> getById(Long TaskId){ return taskRepository.findById(TaskId); }

    public Task createTask(CreateTaskRequest createTaskRequest){
        Task task = new Task();
        task.setContent(createTaskRequest.getContent());
        task.setTitle(createTaskRequest.getTitle());
        task.setStatus(createTaskRequest.getStatus());
        taskRepository.save(task);
        return task;
    }

    public TaskQueryResult assign(Long personId, Long taskId){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found. Person id: "+personId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found. Task id: "+taskId));
        taskRepository.assign(personId, taskId);
        return new TaskQueryResult(task, person);
    }

    public TaskQueryResult generated(Long personId, Long taskId){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found. Person id: "+personId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found. Task id: "+taskId));
        taskRepository.generated(personId, taskId);
        return new TaskQueryResult(task, person);
    }

    public Task findTaskByTitle(String header) {

        return taskRepository.findTaskByTitle(header)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }
}
