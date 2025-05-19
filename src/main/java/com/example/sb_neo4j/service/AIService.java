package com.example.sb_neo4j.service;

import com.example.sb_neo4j.dto.AIQueryDTO;
import com.example.sb_neo4j.dto.AIResponseDTO;
import com.example.sb_neo4j.repository.PersonRepository;
import com.example.sb_neo4j.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {

    private final  WebClient webClient;
    private final  TaskRepository taskRepository;
    private final PersonRepository personRepository;

    public AIService(TaskRepository taskRepository, PersonRepository personRepository) {
        this.webClient = WebClient.builder()
                .baseUrl("http://127.0.0.1:5000/api/v1")
                .build();
        this.taskRepository = taskRepository;
        this.personRepository = personRepository;
    }

    public Mono<AIResponseDTO> executeQuery(){
        // TODO add project reference
        AIQueryDTO requestBody = new AIQueryDTO(taskRepository.findAll(), personRepository.findAll());

        return webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(AIResponseDTO.class);
    }
}
