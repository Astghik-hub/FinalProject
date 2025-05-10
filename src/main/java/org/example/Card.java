package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public abstract class Card implements Comparable<Card> {
    protected int id;
    protected Status status;
    protected Owner owner;
    protected int balance;
    protected Monthly monthly;
    protected boolean isMonthly;
    protected Weekly weekly;
    protected boolean isWeekly;
    protected Stack<Transaction> transactions;

    protected static String idFilePath = "src/main/resources/nextId.txt";
    protected static File idFile = new File(idFilePath);
    protected static int nextId = readIdFromFile(idFile);

    public Card() {
        this.id = nextId++;
        writeIdToFile(idFile);
        this.status = null;
        this.owner = new Owner("fname", "lname");
        this.balance = 0;
        this.monthly = new Monthly();
        this.isMonthly = false;
        this.weekly = new Weekly();
        this.isWeekly = false;
        this.transactions = new Stack<>();
    }

    public Card(Owner owner) {
        this.id = nextId++;
        writeIdToFile(idFile);
        this.owner = owner;
        this.balance = 0;
        this.monthly = new Monthly();
        this.isMonthly = false;
        this.weekly = new Weekly();
        this.isWeekly = false;
        this.transactions = new Stack<>();
    }

    public Card(int id, Status status, Owner owner, int balance, Monthly monthly, boolean isMonthly, Weekly weekly, boolean isWeekly) {
        this.id = id;
        this.status = status;
        this.owner = owner;
        this.balance = balance;
        this.monthly = monthly;
        this.isMonthly = isMonthly;
        this.weekly = weekly;
        this.isWeekly = isWeekly;
        this.transactions = new Stack<>();
    }

    /**
     * shows what kind of bus passes the user has in their card
     */
    public void checkCard() {
        System.out.printf("Balance: %d ticket(s)\n", balance);

        if (monthly.recharge()) {
            System.out.println("Your card is charged for the month");
        } else {
            isMonthly = false;
            System.out.println("Your card is not charged for the month");
        }

        if (weekly.recharge()) {
            System.out.println("Your card is charged for the week");
        } else {
            isWeekly = false;
            System.out.println("Your card is not charged for the week");
        }
    }

    /**
     * allows user to add trips to their card
     *
     * @param numTrips the number of trips the user wants to add
     */
    public void addTrips(int numTrips) {
        IndividualTrip trip = new IndividualTrip();

        balance += numTrips;
        transactions.add(new Transaction(trip.price * numTrips, trip));
    }

    /**
     * allows user to charge their card for the month
     *
     * @param price the price of the monthly pass
     */
    public void addMonthly(double price) {
        monthly.price = price;
        monthly.setPurchaseDate(LocalDateTime.now());
        isMonthly = true;
        transactions.add(new Transaction(price, monthly));
    }

    /**
     * allows user to charge their cord for the week
     *
     * @param price the price of the weekly pass
     */
    public void addWeekly(double price) {
        weekly.price = price;
        weekly.setPurchaseDate(LocalDateTime.now());
        isWeekly = true;
        transactions.add(new Transaction(price, weekly));
    }

    /**
     * allows user to cancel the latest transaction
     */
    public void cancel(Transaction transaction) {
        if (transaction.getTicket() instanceof IndividualTrip) {
            balance -= (int) (transaction.getAmount() / transaction.getTicket().price);
        }

        if (transaction.getTicket() instanceof Monthly) {
            if (transactions.size() == 1 || !(transactions.get(transactions.size() - 2).getTicket() instanceof Monthly)) {
                monthly.setPurchaseDate(LocalDateTime.MIN);
                isMonthly = false;
            } else {
                monthly.setPurchaseDate(transactions.get(transactions.size() - 2).getDateMade());
            }
        }

        if (transaction.getTicket() instanceof Weekly) {
            if (transactions.size() == 1 || !(transactions.get(transactions.size() - 2).getTicket() instanceof Weekly)) {
                weekly.setPurchaseDate(LocalDateTime.MIN);
                isWeekly = false;
            } else {
                weekly.setPurchaseDate(transactions.get(transactions.size() - 2).getDateMade());
            }
        }

        transactions.pop();
    }

    /**
     * reads what the current id should be
     *
     * @param file the file where the id is written
     * @return the id that the card should have
     */
    public static int readIdFromFile(File file) {
        int fileId;
        try {
            if (file.length() == 0) {
                fileId = 1;
            } else {
                Scanner sc = new Scanner(file);
                fileId = sc.nextInt();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return fileId;
    }

    /**
     * overwrites the current id to the next one
     *
     * @param file the file to overwrite
     */
    public static void writeIdToFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(nextId + "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sorts the cards by id
     *
     * @param o the object to be compared.
     * @return the sorted cards
     */
    @Override
    public int compareTo(Card o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && balance == card.balance && isMonthly == card.isMonthly && isWeekly == card.isWeekly && Objects.equals(owner, card.owner) && Objects.equals(status, card.status) && Objects.equals(transactions, card.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, id, status, balance, isMonthly, isWeekly, transactions);
    }

    @Override
    public String toString() {
        return "Card{" +
               "owner=" + owner +
               ", id=" + id +
               ", status='" + status + '\'' +
               ", balance=" + balance +
               ", isMonthly=" + isMonthly +
               ", isWeekly=" + isWeekly +
               ", transactions=" + transactions +
               '}';
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean getIsMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public boolean getIsWeekly() {
        return isWeekly;
    }

    public void setWeekly(boolean weekly) {
        isWeekly = weekly;
    }

    public Stack<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Stack<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Monthly getMonthly() {
        return monthly;
    }

    public void setMonthly(Monthly monthly) {
        this.monthly = monthly;
    }

    public Weekly getWeekly() {
        return weekly;
    }

    public void setWeekly(Weekly weekly) {
        this.weekly = weekly;
    }

    public enum Status {
        STUDENT, NORMAL
    }

    public static class CardComparator implements Comparator<Card> {
        private SortType sortType;

        public CardComparator(SortType sortType) {
            this.sortType = sortType;
        }

        /**
         * sorts the cards depending on the sortType
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return the sorted cards
         */
        @Override
        public int compare(Card o1, Card o2) {
            return switch (sortType) {
                case FNAME -> o1.owner.getFname().compareTo(o2.owner.getFname()) * 10000
                              + o1.status.compareTo(o2.status) * 100
                              + Integer.compare(o1.id, o2.id);
                case LNAME -> o1.owner.getLname().compareTo(o2.owner.getLname()) * 10000
                              + o1.status.compareTo(o2.status) * 100
                              + Integer.compare(o1.id, o2.id);
            };
        }

        public enum SortType {
            FNAME, LNAME
        }
    }
}
