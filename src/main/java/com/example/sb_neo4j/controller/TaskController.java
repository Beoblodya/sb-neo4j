package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.dto.TaskAssignmentDTO;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreateTaskRequest;
import com.example.sb_neo4j.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Task")
public class TaskController {
    //Контроллер для тасков
    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    //Получение всех тасков
    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTask(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    //Создание таска в базе
    //На вход json с названием
    //На выход json с названием
    @PostMapping("/create")
    public ResponseEntity<CreateTaskRequest> taskCreate(@RequestBody CreateTaskRequest request){
        Task task = taskService.createTask(request);

        CreateTaskRequest responseTask = new CreateTaskRequest(task.getTitle(), task.getContent(), task.getStatus());

        return new ResponseEntity<>(responseTask, HttpStatus.CREATED);
    }


    //Создание связи между таском и участником
    //На вход json с именем участника и названием таска
    //На выход json с именем участник и названием таска
    @PostMapping("/assign")
    public ResponseEntity<TaskAssignmentDTO> assign(@RequestBody TaskAssignmentDTO request){
        TaskQueryResult taskQueryResult = taskService.assign(request.getName(), request.getTitle());

        TaskAssignmentDTO response = new TaskAssignmentDTO(taskQueryResult.getPerson().getName(), taskQueryResult.getTask().getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Создание связи между таском и участником с помощью ии
    //На вход json с именем участника и названием таска
    //На выход json с именем участник и названием таска
    @PostMapping("/generated")
    public ResponseEntity<TaskAssignmentDTO> generated(@RequestBody TaskAssignmentDTO request){
        TaskQueryResult taskQueryResult = taskService.generated(request.getName(), request.getTitle());

        TaskAssignmentDTO response = new TaskAssignmentDTO(taskQueryResult.getPerson().getName(), taskQueryResult.getTask().getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
