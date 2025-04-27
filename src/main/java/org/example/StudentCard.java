package org.example;

public class StudentCard extends Card{
    public StudentCard(Owner owner) {
        super(owner);
        this.status = Status.STUDENT;
    }
}
