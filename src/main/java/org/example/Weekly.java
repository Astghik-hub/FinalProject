package org.example;

public class Weekly extends Ticket implements Rechargeable {

    public Weekly(double price) {
        super(price);
    }

    @Override
    public boolean recharge() {
        return false;
    }
}
