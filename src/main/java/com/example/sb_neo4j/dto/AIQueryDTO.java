package com.example.sb_neo4j.dto;

import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Task;

import java.util.ArrayList;
import java.util.List;

public class AIQueryDTO {
    private List<Task>  tasks ;
    private List<Person> users;

    public AIQueryDTO(List<Task> tasks, List<Person> users) {
        this.tasks = tasks;
        this.users = users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Person> getUsers() {
        return users;
    }

    public void setUsers(List<Person> users) {
        this.users = users;
    }
}
