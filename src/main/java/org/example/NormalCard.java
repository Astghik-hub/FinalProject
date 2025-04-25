package org.example;

public class NormalCard extends Card {
    public NormalCard(Owner owner, String status) {
        super(owner, status);
    }

    @Override
    public void addMonthly() {
        double normalPrice = 100;
        Monthly monthly = new Monthly(normalPrice);

        while (monthly.recharge()) {
            isMonthly = true;
        }
    }

    @Override
    public void addWeekly() {

    }
}
