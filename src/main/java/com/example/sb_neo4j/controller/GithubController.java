package com.example.sb_neo4j.controller;

import com.example.sb_neo4j.dto.TaskDTO;
import com.example.sb_neo4j.model.Task;
import com.example.sb_neo4j.service.GithubService;
import com.example.sb_neo4j.util.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class GithubController {

    private final GithubService service;
    private final JsonParser parser;

    public GithubController(GithubService service, JsonParser parser) {
        this.service = service;
        this.parser = parser;
    }

    @QueryMapping
    public List<TaskDTO> tasksOfUser(){
        return new ArrayList<TaskDTO>();
    }

    @GetMapping("/get-issues/{owner}/{repo}/{projectNumber}")
    public Mono<ResponseEntity<List<TaskDTO>>> getIssues(
            @PathVariable String owner,
            @PathVariable String repo,
            @PathVariable int projectNumber,
            @RequestHeader("Authorization") String authHeader
    ) {
        return service.getIssues(owner, repo, projectNumber, authHeader.replace("Bearer ", ""))
                .flatMap(response -> {
                    try {
                        // Предполагаем, что response.getBody() возвращает данные, которые нужно распарсить
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(response);
                        List<TaskDTO> result = parser.parseIssues(json);
                        return Mono.just(ResponseEntity.ok(result));
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                })
                .defaultIfEmpty(ResponseEntity.notFound().build()) // Если Mono пустой: HTTP 404 Not Found
                .onErrorResume(e -> { // Обработка ошибок
                    System.err.println("Error fetching issues: " + e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()); // HTTP 500
                });
    }

    @GetMapping("/get-collaborators/{owner}/{repo}")
    public Mono<ResponseEntity<Map>> getCollaborators(
            @PathVariable String owner,
            @PathVariable String repo,
            @RequestHeader("Authorization") String authHeader
    ){
        return service.getCollaborators(owner, repo, authHeader.replace("Bearer ", ""))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> {
                    System.out.println("Error fetching collaborators: "+e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}
