package com.example.sb_neo4j.util;

import com.example.sb_neo4j.dto.TaskDTO;
import com.example.sb_neo4j.dto.githubissuesDTO.A_IssuesDataDTO;
import com.example.sb_neo4j.dto.githubissuesDTO.Content;
import com.example.sb_neo4j.dto.githubissuesDTO.ItemNode;
import com.example.sb_neo4j.model.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

    public List<TaskDTO> parseIssues(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        A_IssuesDataDTO issuesData = mapper.readValue(json, A_IssuesDataDTO.class);
        List<ItemNode> itemNodes = issuesData
                .getData()
                .getRepository()
                .getProjectV2()
                .getItems()
                .getNodes();
        List<TaskDTO> tasks = new ArrayList<>();
        for (ItemNode node : itemNodes){
            Content content = node.getContent();
            tasks.add(new TaskDTO(
                    content.getTitle(),
                    content.getBody(),
                    content.getState()
            ));
        }
        return tasks;
    }
}
