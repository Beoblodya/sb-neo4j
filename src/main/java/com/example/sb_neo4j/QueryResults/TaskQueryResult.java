package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Task;

public class TaskQueryResult {
    private Task task;
    private Person person;

    public TaskQueryResult() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
