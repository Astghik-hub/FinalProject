package org.example;

import java.util.*;

public class UserInputManager {
    private Card card;

    public static Map<Integer, String> welcomeMenu = new TreeMap<>();
    public static Map<Integer, String> mainMenu = new TreeMap<>();
    public static Map<Integer, String> buyMenu = new TreeMap<>();
    public static Map<Integer, String> proceedMenu = new TreeMap<>();

    private static final int PASSWORD = 91476;

    /**
     * asks the user to put their id if their registered, if they're not it will allow them to register
     */
    public void welcomeMenuOption() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                displayWelcomeMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> loginOption();
                    case 2 -> Accounts.register();
                    case 3 -> forgotId();
                    case 4 -> {
                        System.out.println("Thank you for using the machine");
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again");
                sc.nextLine();
            }
        }
    }

    /**
     * allows the user to login to their account
     */
    public void loginOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your card ID. Type 'b' if you want to go back");

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("b")) {
                    return;
                }

                int id = Integer.parseInt(input);
                this.card = Accounts.findCard(id);

                while (card == null) {
                    System.out.println("Card ID doesn't exist. Please try again or register if you haven't already. " +
                                       "Type 'b' if you want to go back");
                    input = sc.nextLine();
                    if (input.equalsIgnoreCase("b")) {
                        return;
                    }

                    id = Integer.parseInt(input);
                    this.card = Accounts.findCard(id);
                }

                System.out.println("Login successful. Welcome " + card.owner.toString());
                mainMenuOption();
                return;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'b' to go back.");
            }
        }
    }


    /**
     * once the user puts their id, they decide if they want to buy tickets, check their card or cancel a transaction
     */
    public void mainMenuOption() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                displayMainMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> card.checkCard();
                    case 2 -> buyMenuOption();
                    case 3 -> card.cancel();
                    case 4 -> {
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again");
                sc.nextLine();
            }
        }
        mainMenuOption();
    }

    /**
     * allows the user to decide what type of ticket to buy
     */
    public void buyMenuOption() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                displayBuyMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> addTripsMenuOption();
                    case 2 -> addMonthlyMenuOption();
                    case 3 -> addWeeklyMenuOption();
                    case 4 -> {
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again");
                sc.nextLine();
            }
        }
    }

    /**
     * allows user to buy trips
     */
    public void addTripsMenuOption() {
        Scanner sc = new Scanner(System.in);

        int numTrips;
        while (true) {
            try {
                System.out.print("Amount of tickets you wish to buy: ");
                numTrips = sc.nextInt();
                if (numTrips <= 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }

        System.out.printf("Price: %.2f $\n", IndividualTrip.tripPrice * numTrips);

        while (true) {
            try {
                displayProceedMenu();
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        card.addTrips(numTrips);
                        System.out.printf("%d Ticket(s) bought successfully\n", numTrips);
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                    default -> System.out.println("Invalid option. Please choose 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
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

        System.out.printf("Price: %.2f\n", price);

        while (true) {
            try {
                displayProceedMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        card.addMonthly(price);
                        System.out.println("Bus pass bought successfully\n");
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please try again");
                sc.nextLine();
            }
        }
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

        System.out.printf("Price: %.2f\n", price);

        while (true) {
            try {
                displayProceedMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        card.addWeekly(price);
                        System.out.println("Bus pass bought successfully\n");
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please try again");
                sc.nextLine();
            }
        }
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
    public static void forgotId() {
        //TODO
    }

    /**
     *
     */
    public static void displayWelcomeMenu() {
        Map<Integer, String> menu = welcomeMenu;
        menu.put(1, "Login");
        menu.put(2, "Register");
        menu.put(3, "Forgot your id?");
        menu.put(4, "Exit");
        printMaps(menu);
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
     *
     * @param menu the map to print
     */
    public static void printMaps(Map<Integer, String> menu) {
        menu.forEach((key, value) -> System.out.printf("[%d] %s\n", key, value));
    }
}
