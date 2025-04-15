package com.example.sb_neo4j.QueryResults;

import com.example.sb_neo4j.model.Person;

public class PersonRelationQueryResult {
    private Person person1;

    private Person person2;

    public PersonRelationQueryResult() {
    }

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }
}
