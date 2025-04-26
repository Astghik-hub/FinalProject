package org.example;

import java.util.*;

public abstract class Card implements Comparable<Card> {
    protected Owner owner;
    protected int id;
    protected String status;
    protected int balance;
    protected Monthly monthly;
    protected boolean isMonthly;
    protected Weekly weekly;
    protected boolean isWeekly;
    protected Stack<Transaction> transactions;

    protected static int nextId = 0;

    public Card(Owner owner, String status) {
        this.owner = owner;
        this.status = status;
        this.balance = 0;
        this.monthly = new Monthly();
        this.isMonthly = false;
        this.weekly = new Weekly();
        this.isWeekly = false;
        this.transactions = new Stack<>();
        this.id = nextId++;
    }

    /**
     * allows the user to add individual trips
     */
    public void addTrips() {
        double tripPrice = 3.75;
        IndividualTrips trip = new IndividualTrips(tripPrice);

        Scanner sc = new Scanner(System.in);
        System.out.println("Amount of tickets you wish to buy: ");
        int numTrips = sc.nextInt();
        System.out.printf("Price: %.2f $", trip.price * numTrips);

        UserInputManager.displayProceedMenu();

        int choice = sc.nextInt();
        if (choice == 1) {
            balance += numTrips;
            System.out.printf("%d Ticket(s) bought successfully", numTrips);
            transactions.add(new Transaction(trip.price * numTrips, trip));
        }

        if (choice == 2) {
            addTrips();
        }
        //TODO exception handling
    }

    /**
     * allows user to charge their cord for the month
     */
    public abstract void addMonthly();

    /**
     * allows user to charge their cord for the week
     */
    public abstract void addWeekly();

    /**
     * shows what kind of bus passes the user has in their card
     */
    public void checkCard() {
        System.out.printf("Balance: %d tickets", balance);

        if (monthly.recharge()) {
            System.out.printf("Charged for the month: %s", "Yes");
        } else {
            isMonthly = false;
            System.out.printf("Charged for the month: %s", "No");
        }

        if (weekly.recharge()) {
            System.out.printf("Charged for the week: %s", "Yes");
        } else {
            isWeekly = false;
            System.out.printf("Charged for the week: %s", "No");

        }
    }

    @Override
    public int compareTo(Card o) {
        return this.status.compareTo(o.status) * 100
               + this.id - o.id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public boolean isWeekly() {
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
}
