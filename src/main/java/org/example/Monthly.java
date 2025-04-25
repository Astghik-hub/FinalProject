package org.example;

import java.time.LocalDateTime;

public class Monthly extends Ticket implements Rechargeable {
    private LocalDateTime purchaseDate;

    public Monthly(double price) {
        super(price);
        purchaseDate = LocalDateTime.now();
    }

    @Override
    public boolean recharge() {
        return purchaseDate.getMonth().equals(LocalDateTime.now().getMonth());
    }
}
