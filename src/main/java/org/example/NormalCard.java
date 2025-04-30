package org.example;

import java.util.Stack;

public class NormalCard extends Card {
    public NormalCard(Owner owner) {
        super(owner);
        this.status = Status.NORMAL;
    }

    public NormalCard() {
        super();
        this.status = Status.NORMAL;
    }

    public NormalCard(int id, Status status, Owner owner, int balance, boolean isMonthly, boolean isWeekly) {
        super(id, status, owner, balance, isMonthly, isWeekly);
    }
}
