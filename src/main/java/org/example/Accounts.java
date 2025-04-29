package org.example;

import java.util.Scanner;
import java.util.TreeSet;

public class Accounts {
    public static TreeSet<Card> cards = new TreeSet<>();

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
        writeToFile(card);
    }

    /**
     * writes the registered card to a file
     * @param card the card that was registered
     */
    public static void writeToFile(Card card) {
        //TODO
    }

    /**
     * finds the card associated with the id
     * @param id the id of the card to find
     * @return the card associated with the id
     */
    public static Card findCard(int id) {
        //TODO
    }
}
