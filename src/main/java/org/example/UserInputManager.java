package org.example;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class UserInputManager {
    private Card card;

    public static Map<Integer, String> mainMenu;
    public static Map<Integer, String> buyMenu;
    public static Map<Integer, String> proceedMenu;
    public static Map<Integer, String> cancelMenu;

    private static final int PASSWORD = 91476;

    /**
     * asks the user to put their id if their registered, if they're not it will allow them to register
     */
    public void welcomeMenuOption() {
        //TODO
    }

    /**
     * once the user puts their id, they decide if they want to buy tickets, check their card or cancel a transaction
     */
    public void mainMenuOption() {
        //TODO
    }

    /**
     * allows user to check the bus passes they have on their card
     */
    public void checkCardOption() {
        //TODO
    }

    /**
     * allows the user to decide what type of ticket to buy
     */
    public void buyMenuOption() {
        //TODO
    }

    /**
     * allows user to buy trips
     */
    public void addTripsMenuOption() {
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
                    addTripsMenuOption();
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
    public void addMonthlyMenuOption() {
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
                    a++;
                    buyMenuOption();
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
    public void addWeeklyMenuOption() {
        Scanner sc = new Scanner(System.in);
        double price;
        if (card.status.equals(Card.Status.STUDENT)) {
            price = Weekly.discountPrice;
        } else price = Weekly.normalPrice;

        System.out.printf("Price: %.2f", price);
        displayProceedMenu();

        int a = 0;
        while (a == 0) {
            try {
                int choice = sc.nextInt();
                if (choice == 1) {
                    card.addWeekly(price);
                    a++;
                } else if (choice == 2) {
                    a++;
                    buyMenuOption();
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
     * allows user to cancel their latest transaction
     */
    public void cancelMenuOption() {
        //TODO
    }

    /**
     * if the user forgot their id, it will allow them to see all the accounts, if they put the right password
     */
    public void forgotId() {
        //TODO
    }

    /**
     * prints the main menu
     */
    public static void displayMainMenu() {
        //TODO
    }

    /**
     * prints the buy menu
     */
    public static void displayBuyMenu() {
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

    /**
     * prints the cancel menu
     */
    public static void displayCancelMenu() {
        //TODO
    }

    /**
     * displays the accounts based on the sortType
     *
     * @param sortType the way the user wants the accounts to be sorted
     */
    public static void displayAccountsOption(String sortType) {
        //TODO
    }
}
