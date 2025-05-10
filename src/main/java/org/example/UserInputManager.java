package org.example;

import java.util.*;

public class UserInputManager {
    private Card card;

    public static Map<Integer, String> welcomeMenu = new TreeMap<>();
    public static Map<Integer, String> mainMenu = new TreeMap<>();
    public static Map<Integer, String> buyMenu = new TreeMap<>();
    public static Map<Integer, String> proceedMenu = new TreeMap<>();

    private static final int PASSWORD = 91476;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     * asks the user to put their id if their registered, if they're not it will allow them to register
     */
    public void welcomeMenuOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "~~Welcome to the Opus Card Machine~~" + ANSI_RESET);
        while (true) {
            try {
                displayWelcomeMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> loginOption();
                    case 2 -> Accounts.register();
                    case 3 -> forgotId();
                    case 4 -> {
                        System.out.println(ANSI_BLUE + "Thank you for using the machine" + ANSI_RESET);
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid input, please try again" + ANSI_RESET);
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
                    System.out.println(ANSI_RED + "Card ID doesn't exist. Please try again or register if you haven't already. " +
                                       "Type 'b' if you want to go back" + ANSI_RESET);
                    input = sc.nextLine();
                    if (input.equalsIgnoreCase("b")) {
                        return;
                    }

                    id = Integer.parseInt(input);
                    this.card = Accounts.findCard(id);
                }

                System.out.println("Login successful. Welcome " + ANSI_BLUE + card.owner.toString() + ANSI_RESET);
                mainMenuOption();
                return;

            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter a valid number or 'b' to go back." + ANSI_RESET);
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
                System.out.println(ANSI_YELLOW + "**If you log out, you cannot cancel your current transactions" + ANSI_RESET);
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> card.checkCard();
                    case 2 -> buyMenuOption();
                    case 3 -> cancelMenuOption();
                    case 4 -> {
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid input, please try again" + ANSI_RESET);
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
                System.out.println(ANSI_RED + "Invalid input, please try again" + ANSI_RESET);
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
                    System.out.println(ANSI_RED + "Please enter a positive number." + ANSI_RESET);
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
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
                        Accounts.getCards().remove(card);
                        card.addTrips(numTrips);
                        Accounts.getCards().add(card);
                        Accounts.writeToFile(Accounts.getAccountsFile());
                        System.out.printf(ANSI_GREEN + "%d Ticket(s) bought successfully\n" + ANSI_RESET, numTrips);
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                    default -> System.out.println(ANSI_RED + "Invalid option. Please choose 1 or 2." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
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

        System.out.printf("Price: %.2f $\n", price);

        while (true) {
            try {
                displayProceedMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        Accounts.getCards().remove(card);
                        card.addMonthly(price);
                        Accounts.getCards().add(card);
                        Accounts.writeToFile(Accounts.getAccountsFile());
                        System.out.println(ANSI_GREEN + "Bus pass bought successfully\n" + ANSI_RESET);
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid entry, please try again" + ANSI_RESET);
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

        System.out.printf("Price: %.2f $\n", price);

        while (true) {
            try {
                displayProceedMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        Accounts.getCards().remove(card);
                        card.addWeekly(price);
                        Accounts.getCards().add(card);
                        Accounts.writeToFile(Accounts.getAccountsFile());
                        System.out.println("Bus pass bought successfully\n");
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid entry, please try again" + ANSI_RESET);
                sc.nextLine();
            }
        }
    }

    /**
     * allows user to cancel their latest transaction
     */
    public void cancelMenuOption() {
        if (card.transactions.isEmpty()) {
            System.out.println(ANSI_RED + "There are no transactions to cancel" + ANSI_RESET);
            return;
        }

        Scanner sc = new Scanner(System.in);
        Transaction transaction = card.transactions.peek();
        System.out.println(ANSI_YELLOW + "**You can only cancel the latest transaction" + ANSI_RESET);
        System.out.printf("Transaction to cancel: %s, %.2f $\n", transaction.getTicket().name, transaction.getAmount());

        while (true) {
            try {
                displayProceedMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        Accounts.getCards().remove(card);
                        card.cancel(transaction);
                        Accounts.getCards().add(card);
                        Accounts.writeToFile(Accounts.getAccountsFile());
                        System.out.println(ANSI_GREEN + "Transaction canceled successfully\n" + ANSI_RESET);
                        return;
                    }
                    case 2 -> {
                        return;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid entry, please try again" + ANSI_RESET);
                sc.nextLine();
            }
        }
    }

    /**
     * if the user forgot their id, it will allow them to see all the accounts, if they put the right password
     */
    public void forgotId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the password or ask someone who knows it. To go back type 'b'");

        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("b")) {
                return;
            }

            try {
                if (Integer.parseInt(input) == PASSWORD) {
                    displayAccountsOption();
                    return;
                } else {
                    System.out.println(ANSI_RED + "Wrong password, try again or press 'b' to go back" + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter a number or 'b' to go back." + ANSI_RESET);
            }
        }
    }

    /**
     * displays the accounts based on the sortType
     */
    public void displayAccountsOption() {
        Scanner sc = new Scanner(System.in);
        String sortType;
        while (true) {
            System.out.println("Would you like to sort the cards by first name (f) or last name (l)?");
            sortType = sc.next();
            if (sortType.equalsIgnoreCase("f") || sortType.equalsIgnoreCase("l")) {
                break;
            } else {
                System.out.println(ANSI_RED + "Invalid sort type. Please enter 'f' or 'l'." + ANSI_RESET);
            }
            sc.nextLine();
        }

        List<Card> cards = new ArrayList<>(Accounts.getCards().stream().toList());
        if (sortType.equalsIgnoreCase("f")) {
            Collections.sort(cards, new Card.CardComparator(Card.CardComparator.SortType.FNAME));
        } else {
            Collections.sort(cards, new Card.CardComparator(Card.CardComparator.SortType.LNAME));
        }

        printArrayList(cards);
    }

    /**
     * prints the welcome menu
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
        menu.put(4, "Log out");
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
     * format prints maps
     *
     * @param menu the map to print
     */
    public static void printMaps(Map<Integer, String> menu) {
        menu.forEach((key, value) -> System.out.printf("[%d] %s\n", key, value));
    }

    /**
     * prints an arraylist of cards
     *
     * @param cardList the arraylist of cards to print
     */
    public void printArrayList(List<Card> cardList) {
        cardList.forEach(card -> System.out.printf("%s, %s, %05d, %s, %d, %b, %b\n", card.owner.getFname(), card.owner.getLname(), card.id, card.status, card.balance, card.isMonthly, card.isWeekly));
    }
}
