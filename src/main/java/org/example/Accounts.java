package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        try (Scanner scanner = new Scanner(accountsFile)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");
                int id = Integer.parseInt(elements[0]);
                Card.Status status = Card.Status.valueOf(elements[1]);
                String fname = elements[2];
                String lname = elements[3];
                int balance = Integer.parseInt(elements[4]);
                boolean isMonthly = Boolean.parseBoolean(elements[5]);
                boolean isWeekly = Boolean.parseBoolean(elements[6]);

                if (status.equals(Card.Status.STUDENT)) {
                    cards.add(new StudentCard(id, status, new Owner(fname, lname), balance, isMonthly, isWeekly));
                } else if (status.equals(Card.Status.NORMAL)) {
                    cards.add(new NormalCard(id, status, new Owner(fname, lname), balance, isMonthly, isWeekly));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        makeMap();
    }

    /**
     * writes the registered card to a file
     *
     * @param card the card that was registered
     * @param file the file in which the card has to be written
     */
    public static void writeToFile(Card card, File file) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(card.getId() + ",");
                fileWriter.write(card.getStatus() + ",");
                fileWriter.write(card.getOwner().getFname() + ",");
                fileWriter.write(card.getOwner().getLname() + ",");
                fileWriter.write(card.getBalance() + ",");
                fileWriter.write(card.isWeekly() + ",");
                fileWriter.write(card.isWeekly() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * puts the ids as keys and the cards as values in the map
     */
    public static void makeMap() {
        ArrayList<Card> cardList = new ArrayList<>(cards);
        for (int  i = 0; i < cards.size(); i++) {
            idMap.put(cardList.get(i).getId(), cardList.get(i));
        }
    }

    /**
     * finds the card associated with the id
     *
     * @param id the id of the card to find
     * @return the card associated with the id
     */
    public static Card findCard(int id) {
        return idMap.getOrDefault(id, null);
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

    public static File getAccountsFile() {
        return accountsFile;
    }

    public static void setAccountsFile(File accountsFile) {
        Accounts.accountsFile = accountsFile;
    }

    public static Map<Integer, Card> getIdMap() {
        return idMap;
    }

    public static void setIdMap(Map<Integer, Card> idMap) {
        Accounts.idMap = idMap;
    }
}
