package org.example;

import java.util.Objects;

public abstract class Ticket {
    public double price;
    public String name;

    public Ticket() {
        this.price = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(price, ticket.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(price);
    }
}
