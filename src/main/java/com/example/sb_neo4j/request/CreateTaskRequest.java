package com.example.sb_neo4j.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreateTaskRequest {
    private String title;
    private String content;
}
