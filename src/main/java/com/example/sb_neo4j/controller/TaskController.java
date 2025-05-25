package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.dto.*;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreateTaskRequest;
import com.example.sb_neo4j.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Task")
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

    @GetMapping("/get-responsible-for-task/{id}")
    public ResponseEntity<List<Person>> getPersonByTaskId(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getPersonByTaskId(id), HttpStatus.OK);
    }

    //Получение таска по id
    @GetMapping("/get-task/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
    }

    //Создание таска в базе
    //На вход json с названием
    //На выход json с названием
    @PostMapping("/create")
    public ResponseEntity<Task> taskCreate(@RequestBody CreateTaskRequest request){
        Task task = taskService.createTask(request);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    //Создание связи между таском и участником
    //На вход json с именем участника и названием таска
    //На выход json с именем участник и названием таска
    @PostMapping("/assign")
    public ResponseEntity<TaskAssignmentResponseDTO> assign(@RequestBody TaskAssignmentRequestDTO request){
        TaskQueryResult taskQueryResult = taskService.assign(request.getPersonId(), request.getTaskId());

        TaskAssignmentResponseDTO response = new TaskAssignmentResponseDTO(taskQueryResult.getPerson().getId(), taskQueryResult.getPerson().getName(), taskQueryResult.getTask().getId(),
                taskQueryResult.getTask().getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Создание связи между таском и участником с помощью ии
    //На вход json с именем участника и названием таска
    //На выход json с именем участник и названием таска
    @PostMapping("/generated")
    public ResponseEntity<TaskAssignmentResponseDTO> generated(@RequestBody TaskAssignmentRequestDTO request){
        TaskQueryResult taskQueryResult = taskService.generated(request.getPersonId(), request.getTaskId());

        TaskAssignmentResponseDTO response = new TaskAssignmentResponseDTO(taskQueryResult.getPerson().getId(), taskQueryResult.getPerson().getName(), taskQueryResult.getTask().getId(),
                taskQueryResult.getTask().getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/close")
    public ResponseEntity<ChangeTaskStatusDTO> closeTask(@RequestBody ChangeTaskStatusDTO dto){
        return new ResponseEntity<>(dto,
                taskService.closeTask(dto.getTaskId(), dto.getIssuerId())?
                HttpStatus.OK : HttpStatus.FORBIDDEN);

    }

    @PutMapping("/open")
    public ResponseEntity<ChangeTaskStatusDTO> openTask(@RequestBody ChangeTaskStatusDTO dto){
        return new ResponseEntity<>(dto,
                taskService.openTask(dto.getTaskId(), dto.getIssuerId())?
                HttpStatus.OK : HttpStatus.FORBIDDEN);

    }

    @PutMapping("/changeTitle")
    public ResponseEntity<ChangeTaskDTO> changeTaskTitle(@RequestBody ChangeTaskDTO dto){
        return new ResponseEntity<>(dto,
                taskService.changeTaskTitle(dto.getTaskId(), dto.getIssuerId(), dto.getNewParam())?
                        HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    @PutMapping("/changeContent")
    public ResponseEntity<ChangeTaskDTO> changeTaskContent(@RequestBody ChangeTaskDTO dto){
        return new ResponseEntity<>(dto,
                taskService.changeTaskContent(dto.getTaskId(), dto.getIssuerId(), dto.getNewParam())?
                        HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<TaskAssignmentRequestDTO> deleteTaskById(@RequestBody TaskAssignmentRequestDTO dto){
        return new ResponseEntity<>(dto,
                taskService.deleteTaskById(dto.getTaskId(), dto.getPersonId())?
                HttpStatus.OK : HttpStatus.FORBIDDEN);
    }
}
