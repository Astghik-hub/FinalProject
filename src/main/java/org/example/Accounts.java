package org.example;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Accounts {
    private static TreeSet<Card> cards = new TreeSet<>();
    private static String accountsFilePath = "src/main/resources/accounts.csv";
    private static File accountsFile = new File(accountsFilePath);
    private static Map<Integer, Card> idMap = new TreeMap<>();


    /**
     * the user registers, and it gives them the id of their assigned card
     */
    public static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("First name: ");
        String fname = sc.next();
        System.out.println("Last name: ");
        String lname = sc.next();
        System.out.println("Status (student or normal): ");
        String status = sc.next();

        Owner owner = new Owner(fname, lname);
        Card card = null;
        if (status.equalsIgnoreCase("student")) {
            card = new StudentCard(owner);
        }

        if (status.equalsIgnoreCase("normal")) {
            card = new NormalCard(owner);
        }

        assert card != null;
        System.out.printf("Here is your id: %d", card.id);
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
    public static void makeToMap() {
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
