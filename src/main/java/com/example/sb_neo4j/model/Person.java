package com.example.sb_neo4j.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Node
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private List<String> skillSet;
}
