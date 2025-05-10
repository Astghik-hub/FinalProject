package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private double amount;
    private Ticket ticket;
    private LocalDateTime dateMade;

    public Transaction(double amount, Ticket ticket) {
        this.dateMade = LocalDateTime.now();
        this.amount = amount;
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount && Objects.equals(dateMade, that.dateMade) && Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateMade, amount, ticket);
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "dateMade=" + dateMade +
               ", amount=" + amount +
               ", ticket=" + ticket +
               '}';
    }

    public LocalDateTime getDateMade() {
        return dateMade;
    }

    public double getAmount() {
        return amount;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
