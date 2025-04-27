package org.example;

public class NormalCard extends Card {
    public NormalCard(Owner owner) {
        super(owner);
        this.status = Status.NORMAL;
    }
}
