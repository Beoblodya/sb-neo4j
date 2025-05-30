package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.QueryResults.TaskQueryResult;
import com.example.sb_neo4j.dto.*;
import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.request.CreateTaskRequest;
import com.example.sb_neo4j.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Получение всех тасков")
    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTask(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @Operation(summary = "Получение ответственного за задачу")
    @GetMapping("/get-responsible-for-task/{id}")
    public ResponseEntity<List<Person>> getPersonByTaskId(@PathVariable Long id){
        List<Person> people = taskService.getPersonByTaskId(id);
        return new ResponseEntity<>(people,
                people.isEmpty()?
                    HttpStatus.NO_CONTENT : HttpStatus.FOUND);
    }

    @Operation(summary = "Получение таска по id")
    @GetMapping("/get-task/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Создание таска в базе")
    @PostMapping("/create")
    public ResponseEntity<Task> taskCreate(@RequestBody CreateTaskRequest request){
        Task task = taskService.createTask(request);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PostMapping("/assign")
    @Operation(summary = "Создание связи между таском и участником")
    public ResponseEntity<TaskAssignmentResponseDTO> assign(@RequestBody TaskAssignmentRequestDTO request){
        TaskQueryResult taskQueryResult = taskService.assign(request.getPersonId(), request.getTaskId());

        TaskAssignmentResponseDTO response = new TaskAssignmentResponseDTO(taskQueryResult.getPerson().getId(), taskQueryResult.getPerson().getName(), taskQueryResult.getTask().getId(),
                taskQueryResult.getTask().getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/generated")
    @Operation(summary = "Создание связи между таском и участником с помощью ии")
    public ResponseEntity<TaskAssignmentResponseDTO> generated(@RequestBody TaskAssignmentRequestDTO request){
        TaskQueryResult taskQueryResult = taskService.generated(request.getPersonId(), request.getTaskId());

        TaskAssignmentResponseDTO response = new TaskAssignmentResponseDTO(taskQueryResult.getPerson().getId(), taskQueryResult.getPerson().getName(), taskQueryResult.getTask().getId(),
                taskQueryResult.getTask().getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/close")
    @Operation(summary = "Закрытие задачи пользователем")
    public ResponseEntity<TaskIdDTO> closeTask(@RequestBody TaskIdDTO dto){
        return new ResponseEntity<>(dto,
                taskService.closeTask(dto.getTaskId())?
                HttpStatus.OK : HttpStatus.FORBIDDEN);

    }

    @PutMapping("/open")
    @Operation(summary = "Открытие задачи пользователем")
    public ResponseEntity<TaskIdDTO> openTask(@RequestBody TaskIdDTO dto){
        return new ResponseEntity<>(dto,
                taskService.openTask(dto.getTaskId())?
                HttpStatus.OK : HttpStatus.FORBIDDEN);

    }

    @PutMapping("/changeTitle")
    @Operation(summary = "Изменение названия задачи пользователем")
    public ResponseEntity<ChangeTaskDTO> changeTaskTitle(@RequestBody ChangeTaskDTO dto){
        return new ResponseEntity<>(dto,
                taskService.changeTaskTitle(dto.getTaskId(), dto.getNewParam())?
                        HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    @PutMapping("/changeContent")
    @Operation(summary = "Изменение описания задачи пользователем")
    public ResponseEntity<ChangeTaskDTO> changeTaskContent(@RequestBody ChangeTaskDTO dto){
        return new ResponseEntity<>(dto,
                taskService.changeTaskContent(dto.getTaskId(), dto.getNewParam())?
                        HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @Operation(summary = "Удаление задачи пользователем")
    public ResponseEntity<TaskIdDTO> deleteTaskById(@PathVariable Long id){
        return new ResponseEntity<>(new TaskIdDTO(id),
                taskService.deleteTaskById(id)?
                HttpStatus.OK : HttpStatus.FORBIDDEN);
    }
}
