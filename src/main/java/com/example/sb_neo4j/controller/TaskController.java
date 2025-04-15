package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/Task")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTask(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    
}
