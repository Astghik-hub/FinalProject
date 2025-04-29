package org.example;

public abstract class Ticket {
    public double price;

    public Ticket() {
        this.price = 0;
    }

    public Ticket(double price) {
        this.price = price;
    }
}
