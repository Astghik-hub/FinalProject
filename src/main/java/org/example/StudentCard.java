package org.example;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StudentCard extends Card{
    public StudentCard(Owner owner, String status) {
        super(owner, status);
    }

    @Override
    public void addMonthly() {
        double discountPrice = 60;
        Map<Integer, String> map = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        map.put(1, "Proceed");
        map.put(2, "Cancel");

        System.out.printf("Price: %f", discountPrice);
        for (int i = 1; i <= map.size(); i++) {
            System.out.printf("[ %d ] %-10s", i, map.get(i));
        }
        int choice = sc.nextInt();
        if (choice == 1) {
            Monthly monthly = new Monthly(discountPrice);
            while (monthly.recharge()) {
                isMonthly = true;
            }
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
