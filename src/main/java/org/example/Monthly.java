package org.example;

import java.time.LocalDateTime;

public class Monthly extends Ticket implements Rechargeable {
    private LocalDateTime purchaseDate;

    public Monthly() {
        super();
        this.purchaseDate = LocalDateTime.now();
    }

    @Override
    public boolean recharge() {
        return purchaseDate.getMonth().equals(LocalDateTime.now().getMonth());
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
