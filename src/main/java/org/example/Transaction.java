package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private LocalDateTime dateMade;
    private int amount;
    private Ticket ticket;

    public Transaction(int amount, Ticket ticket) {
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

    public void setDateMade(LocalDateTime dateMade) {
        this.dateMade = dateMade;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
