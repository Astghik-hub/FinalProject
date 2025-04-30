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
        System.out.printf("Price: %.2f $\n", IndividualTrip.tripPrice * numTrips);
        displayProceedMenu();
        int a = 0;
        while (a == 0) {
            try {
                int choice = sc.nextInt();
                if (choice == 1) {
                    card.addTrips(numTrips);
                    a++;
                } else if (choice == 2) {
                    addTripsMenu();
                    a++;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please try again");
            }
        }
        System.out.printf("%d Ticket(s) bought successfully\n", numTrips);
        //TODO
    }

    /**
     * allows user to buy a monthly pass
     */
    public void addMonthlyMenu() {
        Scanner sc = new Scanner(System.in);
        double price;
        if (card.status.equals(Card.Status.STUDENT)) {
            price = Monthly.discountPrice;
        } else price = Monthly.normalPrice;

        System.out.printf("Price: %.2f", price);
        displayProceedMenu();

        int a = 0;
        while (a == 0) {
            try {
                int choice = sc.nextInt();
                if (choice == 1) {
                    card.addMonthly(price);
                    a++;
                } else if (choice == 2) {

                    //TODO
                    a++;

                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please try again");
            }
        }
        System.out.println("Bus pass bought successfully\n");
        //TODO
    }

    /**
     * allows user to buy a weekly pass
     */
    public void addWeeklyMenu() {
        Scanner sc = new Scanner(System.in);
        double price;
        if (card.status.equals(Card.Status.STUDENT)) {
            price = Weekly.discountPrice;
        } else price = Weekly.normalPrice;

        System.out.printf("Price: %.2f", price);
        displayProceedMenu();

        int a = 0;
        while (a==0) {
            try {
                int choice = sc.nextInt();
                if (choice == 1) {
                    card.addWeekly(price);
                    a++;
                } else if (choice == 2) {

                    //TODO
                    a++;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please try again");
            }
        }
        System.out.println("Bus pass bought successfully\n");
        //TODO
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
