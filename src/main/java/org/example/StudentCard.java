package org.example;

public class StudentCard extends Card {
    public StudentCard(Owner owner) {
        super(owner);
        this.status = Status.STUDENT;
    }

    public StudentCard(int id, Status status, Owner owner, int balance, Monthly monthly, boolean isMonthly, Weekly weekly, boolean isWeekly) {
        super(id, status, owner, balance, monthly, isMonthly, weekly, isWeekly);
    }
}
