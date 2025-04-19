package com.example.sb_neo4j.service;


import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.repository.TaskRepository;
import com.example.sb_neo4j.request.CreateTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks (){
        return taskRepository.findAll();
    }

    public Task createTask(CreateTaskRequest createTaskRequest){
        Task task = new Task();
        task.setContent(createTaskRequest.getContent());
        task.setTitle(createTaskRequest.getTitle());
        task.setStatus(createTaskRequest.getStatus());
        taskRepository.save(task);
        return task;
    }

    public TaskQueryResult assign(String name, String title){
        return taskRepository.assign(name, title);
    }

    public Task findTaskByTitle(String header) {

        return taskRepository.findTaskByTitle(header)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }
}
