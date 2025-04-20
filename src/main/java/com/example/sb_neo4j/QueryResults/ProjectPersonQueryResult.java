package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.model.Person;
import com.example.sb_neo4j.model.Project;

public class ProjectPersonQueryResult {
    private Project project;
    private Person person;

    public ProjectPersonQueryResult() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
