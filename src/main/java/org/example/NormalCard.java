package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;

public class NormalCard extends Card {
    public NormalCard(Owner owner, String status) {
        super(owner, status);
    }

    @Override
    public void addMonthly() {
        double normalPrice = 100;

        System.out.printf("Price: %f", normalPrice);
        UserInputManager.displayProceedMenu();
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        if (choice == 1) {
            monthly.price = normalPrice;
            monthly.setPurchaseDate(LocalDateTime.now());
            isMonthly = true;
            transactions.add(new Transaction(normalPrice, monthly));
        }

        if (choice == 2) {
            addMonthly();
        }
    }

    @Override
    public void addWeekly() {

    }
}
