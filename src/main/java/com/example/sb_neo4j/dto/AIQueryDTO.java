package com.example.sb_neo4j.dto;

import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AIQueryDTO {
    private final List<Task>  tasks ;
    private final List<Person> users;

//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }
//
//    public List<Person> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<Person> users) {
//        this.users = users;
//    }
}
