package org.example;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Card implements Comparable<Card> {
    protected int id;
    protected String status;
    protected Owner owner;
    protected int balance;
    protected Monthly monthly;
    protected boolean isMonthly;
    protected Weekly weekly;
    protected boolean isWeekly;
    protected Stack<Transaction> transactions;

    protected static int nextId = 0;

    public Card() {
        this.owner = new Owner("fnane", "lname");
        this.status = "status";
        this.balance = 0;
        this.monthly = new Monthly();
        this.isMonthly = false;
        this.weekly = new Weekly();
        this.isWeekly = false;
        this.transactions = new Stack<>();
        this.id = nextId++;
    }

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
    public void addTrips(double price, int numTrips) {
        IndividualTrips trip = new IndividualTrips(price);

            balance += numTrips;
            System.out.printf("%d Ticket(s) bought successfully", numTrips);
            transactions.add(new Transaction(trip.price * numTrips, trip));
    }

    /**
     * allows user to charge their cord for the month
     */
    public void addMonthly(double price) {
        monthly.price = price;
        monthly.setPurchaseDate(LocalDateTime.now());
        isMonthly = true;
        transactions.add(new Transaction(price, monthly));
    }

    /**
     * allows user to charge their cord for the week
     */
    public void addWeekly() {

    }

    /**
     * shows what kind of bus passes the user has in their card
     */
    public void checkCard() {
        System.out.printf("Balance: %d tickets", balance);

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
