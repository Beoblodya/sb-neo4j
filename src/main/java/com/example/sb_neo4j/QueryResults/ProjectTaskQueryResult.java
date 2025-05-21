package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.model.Project;
import com.example.sb_neo4j.model.Task;

public class ProjectTaskQueryResult {
    private Project project;
    private Task task;

    public ProjectTaskQueryResult(Project project, Task task) {
        this.project = project;
        this.task = task;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
