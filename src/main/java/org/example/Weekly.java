package org.example;

import java.time.LocalDateTime;

public class Weekly extends Ticket implements Rechargeable {

    private LocalDateTime purchaseDate;

    public Weekly() {
        super();
        this.purchaseDate = LocalDateTime.now();
    }

    @Override
    public boolean recharge() {
        return !(purchaseDate.plusWeeks(1).isAfter(LocalDateTime.now()));
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
