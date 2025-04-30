package org.example;

public class StudentCard extends Card {
    public StudentCard() {
        super();
        this.status = Status.STUDENT;
    }

    public StudentCard(Owner owner) {
        super(owner);
        this.status = Status.STUDENT;
    }

    public StudentCard(int id, Status status, Owner owner, int balance, boolean isMonthly, boolean isWeekly) {
        super(id, status, owner, balance, isMonthly, isWeekly);
    }
}
