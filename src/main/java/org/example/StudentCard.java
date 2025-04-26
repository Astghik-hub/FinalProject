package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;

public class StudentCard extends Card{
    public StudentCard(Owner owner, String status) {
        super(owner, status);
    }

    @Override
    public void addMonthly() {
        double discountPrice = 60;

        System.out.printf("Price: %f", discountPrice);
        UserInputManager.displayProceedMenu();
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        if (choice == 1) {
            monthly.price = discountPrice;
            monthly.setPurchaseDate(LocalDateTime.now());
            isMonthly = true;
            transactions.add(new Transaction(discountPrice, monthly));
        }

        if (choice == 2) {
            addMonthly();
        }

    }

    @Override
    public void addWeekly() {

    }

}
