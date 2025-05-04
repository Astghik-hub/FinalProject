package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

public class Monthly extends Ticket implements Rechargeable {
    private LocalDateTime purchaseDate;

    public static double normalPrice = 100;
    public static double discountPrice = 60;

    public Monthly() {
        super();
        this.purchaseDate = LocalDateTime.MIN;
        this.name = "Monthly pass";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Monthly monthly = (Monthly) o;
        return Objects.equals(purchaseDate, monthly.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), purchaseDate);
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
