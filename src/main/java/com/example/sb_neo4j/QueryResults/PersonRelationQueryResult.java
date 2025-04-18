package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.model.Person;

public class PersonRelationQueryResult {
    private String person1;

    private String person2;

    public PersonRelationQueryResult() {
    }

    public String getPerson1() {
        return person1;
    }

    public void setPerson1(String person1) {
        this.person1 = person1;
    }

    public String getPerson2() {
        return person2;
    }

    public void setPerson2(String person2) {
        this.person2 = person2;
    }
}
