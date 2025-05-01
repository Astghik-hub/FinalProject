package org.example;

import java.io.File;
import java.util.*;

public class Accounts {
    private static TreeSet<Card> cards = new TreeSet<>();
    private static String accountsFilePath = "src/main/resources/accounts.csv";
    private static File accountsFile = new File(accountsFilePath);
    private static Map<Integer, Card> idMap = new TreeMap<>();


    /**
     * the user registers, and it gives them the id of their assigned card
     */
    public static void register() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("First name: ");
//        String fname = sc.next();
//        System.out.println("Last name: ");
//        String lname = sc.next();
//        System.out.println("Status (student or normal): ");
//        String status = sc.next();
//
//        Owner owner = new Owner(fname, lname);
//        Card card = null;
//        if (status.equalsIgnoreCase("student")) {
//            card = new StudentCard(owner);
//        }
//
//        if (status.equalsIgnoreCase("normal")) {
//            card = new NormalCard(owner);
//        }
//
//        assert card != null;
//        System.out.printf("Here is your id: %d", card.id);
//        cards.add(card);
//        writeToFile(card, accountsFile);

        Scanner sc = new Scanner(System.in);
        String fname;
        String lname;
        String status;

        while (true) {
            try {
                System.out.print("First name: ");
                fname = sc.next();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid first name.");
                sc.nextLine();
            }
        }

        while (true) {
            try {
                System.out.print("Last name: ");
                lname = sc.next();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid last name.");
                sc.nextLine();
            }
        }

        while (true) {
            try {
                System.out.print("Status (student or normal): ");
                status = sc.next();
                if (status.equalsIgnoreCase("student") || status.equalsIgnoreCase("normal")) {
                    break;
                } else {
                    System.out.println("Invalid status. Please enter 'student' or 'normal'.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid status.");
                sc.nextLine();
            }
        }

        Owner owner = new Owner(fname, lname);
        Card card = null;

        if (status.equalsIgnoreCase("student")) {
            card = new StudentCard(owner);
        } else if (status.equalsIgnoreCase("normal")) {
            card = new NormalCard(owner);
        }

        assert card != null;
        System.out.printf("Here is your id: %d\n", card.id);
        cards.add(card);
        writeToFile(card, accountsFile);
    }

    /**
     * reads the cards from a file and adds them to the set of cards
     */
    public static void addFromFile(File file) {
        //TODO
    }

    /**
     * writes the registered card to a file
     *
     * @param card the card that was registered
     * @param file the file in which the card has to be written
     */
    public static void writeToFile(Card card, File file) {
        //TODO
    }

    /**
     * puts the ids as keys and the cards as values in the map
     */
    public static void makeMap() {
        //TODO
    }

    /**
     * finds the card associated with the id
     *
     * @param id the id of the card to find
     * @return the card associated with the id
     */
    public static Card findCard(int id) {
        //TODO
        return null;
    }

    /**
     * displays the accounts on the screen
     */
    public static void displayAccounts() {
        //TODO
    }

    public static TreeSet<Card> getCards() {
        return cards;
    }

    public static void setCards(TreeSet<Card> cards) {
        Accounts.cards = cards;
    }

    public static String getAccountsFilePath() {
        return accountsFilePath;
    }

    public static void setAccountsFilePath(String accountsFilePath) {
        Accounts.accountsFilePath = accountsFilePath;
    }
}
