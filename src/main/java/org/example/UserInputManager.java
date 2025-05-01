package org.example;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UserInputManager {
    private Card card;

    public static Map<Integer, String> mainMenu = new TreeMap<>();
    public static Map<Integer, String> buyMenu = new TreeMap<>();
    public static Map<Integer, String> proceedMenu = new TreeMap<>();
    public static Map<Integer, String> cancelMenu = new TreeMap<>();

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

        while (true) {
            try {
                int choice = sc.nextInt();
                if (choice == 1) {
                    card.addMonthly(price);
                    break;
                } else if (choice == 2) {
                    buyMenuOption();
                    break;
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

        while (true) {
            try {
                int choice = sc.nextInt();
                if (choice == 1) {
                    card.addWeekly(price);
                    break;
                } else if (choice == 2) {
                    buyMenuOption();
                    break;
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
        Map<Integer, String> menu = mainMenu;
        menu.put(1, "Check card");
        menu.put(2, "Buy");
        menu.put(3, "Cancel Transaction");
        menu.put(4, "Exit");
        printMaps(menu);
    }

    /**
     * prints the buy menu
     */
    public static void displayBuyMenu() {
        Map<Integer, String> menu = buyMenu;
        menu.put(1, "Individual Ticket");
        menu.put(2, "Monthly Pass");
        menu.put(3, "Weekly Pass");
        menu.put(4, "Exit");
        printMaps(menu);
    }

    /**
     * prints the proceed menu
     */
    public static void displayProceedMenu() {
        Map<Integer, String> menu = proceedMenu;
        menu.put(1, "Proceed");
        menu.put(2, "Cancel");
        printMaps(menu);
    }

    /**
     * displays the accounts based on the sortType
     *
     * @param sortType the way the user wants the accounts to be sorted
     */
    public static void displayAccountsOption(String sortType) {
        //TODO
    }

    /**
     * format prints maps
     * @param menu the map to print
     */
    public static void printMaps(Map<Integer, String> menu) {
        menu.forEach((key, value) -> System.out.printf("[%d] %s\n", key, value));
    }
}