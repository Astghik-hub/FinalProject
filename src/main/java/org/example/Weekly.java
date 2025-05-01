package org.example;

import java.time.LocalDateTime;

public class Weekly extends Ticket implements Rechargeable {
    private LocalDateTime purchaseDate;

    public static double normalPrice = 31;
    public static double discountPrice = 18.5;

    public Weekly() {
        super();
        this.purchaseDate = LocalDateTime.now();
    }

    /**
     * allows the user to check if the weekly pass they bought is still valid
     *
     * @return true if the pass is valid, false if it isn't
     */
    @Override
    public boolean recharge() {
        return purchaseDate.getYear() == LocalDateTime.now().getYear()
               && purchaseDate.getMonth().equals(LocalDateTime.now().getMonth())
               && !(LocalDateTime.now().isAfter(purchaseDate.plusWeeks(1)));
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
