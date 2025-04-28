package org.example;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class UserInputManager {
    private Card card;

    public static Map<Integer, String> proceedMenu;

    /**
     * allows user to buy trips
     */
    public void addTripsMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Amount of tickets you wish to buy: ");
        int numTrips = sc.nextInt();
        System.out.printf("Price: %.2f $\n", Card.tripPrice * numTrips);
        displayProceedMenu();
        int a = 0;
        while (a == 0) {

            try {
                int choice = sc.nextInt();
                if (choice == 1) {
                    a = 1;
                } else if (choice == 2) {
                    a = 2;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please try again");
            }
            if (a == 1) {
                card.addTrips(numTrips);
            } else if (a == 2) {
                addTripsMenu();
            }
        }
        //TODO
    }

    /**
     * allows user to buy a monthly pass
     */
    public void addMonthlyMenu() {
        Scanner sc = new Scanner(System.in);
        double price;
        if (card.status.equals(Card.Status.STUDENT)) {
            price = Card.monthlyDiscountPrice;
        } else price = Card.monthlyNormalPrice;

        System.out.printf("Price: %.2f", price);
        displayProceedMenu();

        try {
            int choice = sc.nextInt();
            if (choice == 1) {
                card.addMonthly(price);
            } else if (choice == 2) {

                //TODO

            } else {
                throw new RuntimeException("Invalid Number, please try again");
            }
        } catch (IllegalNumberException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry, please try again");
        }
    }

    /**
     * allows user to buy a weekly pass
     */
    public void addWeeklyMenu() {
        Scanner sc = new Scanner(System.in);
        double price;
        if (card.status.equals(Card.Status.STUDENT)) {
            price = Card.weeklyDiscountPrice;
        } else price = Card.weeklyNormalPrice;

        System.out.printf("Price: %.2f", price);
        displayProceedMenu();

        try {
            int choice = sc.nextInt();
            if (choice == 1) {
                card.addWeekly(price);
            } else if (choice == 2) {

                //TODO

            } else {
                throw new RuntimeException("Invalid Number, please try again");
            }
        } catch (IllegalNumberException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry, please try again");
        }
    }

    /**
     * prints the proceed menu
     */
    public static void displayProceedMenu() {
        proceedMenu.put(1, "Proceed");
        proceedMenu.put(2, "Cancel");

        for (int i = 1; i <= proceedMenu.size(); i++) {
            System.out.printf("[%d] %-10s", i, proceedMenu.get(i));
        }
    }
}
