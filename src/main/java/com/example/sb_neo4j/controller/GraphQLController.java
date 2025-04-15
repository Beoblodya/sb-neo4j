package com.example.sb_neo4j.controller;

import com.example.sb_neo4j.dto.TaskDTO;
import com.example.sb_neo4j.service.GraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GraphQLController {

    @Autowired
    private GraphQLService service;

    @QueryMapping
    public List<TaskDTO> tasksOfUser(){
        return new ArrayList<TaskDTO>();
    }

    @PostMapping("api/v1/{token}")
    public void setParams(@RequestParam String token){
//        service.setToken(token);
    }

    @GetMapping("/get-issues/{owner}/{repo}/{projectNumber}")
    public Mono<ResponseEntity<Map>> getIssues(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable int projectNumber
    ) {
        return service.getProjectIssues(owner, repo, projectNumber)
                .map(ResponseEntity::ok) // Успешный результат: HTTP 200 OK
                .defaultIfEmpty(ResponseEntity.notFound().build()) // Если Mono пустой: HTTP 404 Not Found
                .onErrorResume(e -> { // Обработка ошибок
                    System.err.println("Error fetching issues: " + e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()); // HTTP 500
                });
    }
}
