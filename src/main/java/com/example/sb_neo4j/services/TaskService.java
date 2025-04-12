package com.example.sb_neo4j.services;


import com.example.sb_neo4j.models.Task;
import com.example.sb_neo4j.repositories.TaskRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks (){
        return taskRepository.findAll();
    }

    public Task findTaskbyHeader(String header) {

        return taskRepository.findTaskbyHeader(header)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }
}
