package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.service.AIService;
import org.springframework.stereotype.Controller;

@Controller
public class AIController {

    private final AIService aiService;


    public AIController(AIService aiService) {
        this.aiService = aiService;
    }
}
