package org.example;

import java.time.LocalDateTime;

public class Monthly extends Ticket implements Rechargeable {
    private LocalDateTime purchaseDate;

    public static double normalPrice = 100;
    public static double discountPrice = 60;

    public Monthly() {
        super();
        this.purchaseDate = LocalDateTime.MIN;
    }

    /**
     * allows the user to check if the monthly pass they bought is still valid
     *
     * @return true if the pass is valid, false if it isn't
     */
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
