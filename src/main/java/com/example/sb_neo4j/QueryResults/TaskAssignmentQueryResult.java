package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.models.Task;
import com.example.sb_neo4j.models.User;

public class TaskAssignmentQueryResult {
    private Task task;
    private User user;

    public TaskAssignmentQueryResult() {
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
