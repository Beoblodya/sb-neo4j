package com.example.sb_neo4j.controller;

import com.example.sb_neo4j.dto.AIResponseDTO;
import com.example.sb_neo4j.service.AIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("api/v1/AI")
public class AIController {
    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    //мнение нейронки по связи задачи с исполнителем
    // вход - автоматический забор данных с бд
    // выход json {"assignments":  [ {"name": "<NAME>", "title": "<TITLE>"} ] }
    // name - имя исполнителя, title - название задачи
    @GetMapping("/relations")
    public Mono<ResponseEntity<AIResponseDTO>> aiAdvice(){
        return aiService.executeQuery()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build()) // Если Mono пустой: HTTP 404 Not Found
                .onErrorResume(e -> { // Обработка ошибок
                    System.err.println("Error binding tasks: " + e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()); // HTTP 500
                });
    }
}
