package org.example;

public class NormalCard extends Card {
    public NormalCard(Owner owner) {
        super(owner);
        this.status = Status.NORMAL;
    }

    public NormalCard() {
        super();
        this.status = Status.NORMAL;
    }

    public NormalCard(int id, Status status, Owner owner, int balance, Monthly monthly, boolean isMonthly, Weekly weekly, boolean isWeekly) {
        super(id, status, owner, balance, monthly, isMonthly, weekly, isWeekly);
    }
}
