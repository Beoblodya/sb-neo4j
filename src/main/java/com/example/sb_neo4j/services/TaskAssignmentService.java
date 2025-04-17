package com.example.sb_neo4j.services;


import com.example.sb_neo4j.QueryResults.TaskAssignmentQueryResult;
import com.example.sb_neo4j.models.Task;
import com.example.sb_neo4j.repositories.TaskRepository;
import com.example.sb_neo4j.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskAssignmentService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskAssignmentService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> findAllTasksofUser(String name){
        return taskRepository.findAllTasksofUser(name);
    }

    public Boolean findAssignmentStatus(String title, String name) {
        return taskRepository.findAssignmentStatus(title, name);
    }

    public TaskAssignmentQueryResult assignTasktoUser(String title, String name) {
        return taskRepository.assignTasktoUser(title, name);
    }
}
