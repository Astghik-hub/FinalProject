package org.example;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class UserInputManager {
    private Card card;

    public static Map<Integer, String> proceedMenu;

    public static void displayProceedMenu() {
        proceedMenu.put(1, "Proceed");
        proceedMenu.put(2, "Cancel");

        for (int i = 1; i <= proceedMenu.size(); i++) {
            System.out.printf("[ %d ] %-10s", i, proceedMenu.get(i));
        }
    }
    public void addTripsMenu() {
        double price = 3.75;
        Scanner sc = new Scanner(System.in);

        System.out.print("Amount of tickets you wish to buy: ");
        int numTrips = sc.nextInt();
        System.out.printf("Price: %.2f $\n", price * numTrips);
        displayProceedMenu();

        try {
            int choice = sc.nextInt();
            if (choice == 1) {
                card.addTrips(price, numTrips);
            } else if (choice == 2) {
                addTripsMenu();
            } else {
                throw new RuntimeException("Invalid Number, please try again");
            }
        } catch (IllegalNumberException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid entry, please try again");
        }
        //TODO ask
    }

    public void addMonthlyMenu() {
        Scanner sc = new Scanner(System.in);
        double price;
        if (card instanceof StudentCard) {
            price = 60;
        } else price = 100;

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
}
