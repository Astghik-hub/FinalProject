package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Monthly extends Ticket implements Rechargeable {
    private LocalDateTime purchaseDate;

    public Monthly() {
        super();
        this.purchaseDate = LocalDateTime.MIN;
    }

    @Override
    public boolean recharge() {
        return purchaseDate.getYear() == LocalDateTime.now().getYear()
               && purchaseDate.getMonth().equals(LocalDateTime.now().getMonth());
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
