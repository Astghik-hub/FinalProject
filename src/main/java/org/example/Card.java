package org.example;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public abstract class Card implements Comparable<Card> {
    protected Owner owner;
    protected int id;
    protected String status;
    protected int balance;
    protected boolean isMonthly;
    protected boolean isWeekly;
    protected Stack<Transaction> transactions;

    protected static int nextId = 0;
    protected static Accounts accounts = new Accounts();

    public Card(Owner owner, String status) {
        this.owner = owner;
        this.status = status;
        this.balance = 0;
        this.isMonthly = false;
        this.isWeekly = false;
        this.id = nextId++;
    }

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
            card = new StudentCard(owner, status);
        }

        if (status.equalsIgnoreCase("normal")) {
           card = new NormalCard(owner, status);
        }

        assert card != null;
        System.out.printf("Here is your id: %d", card.id);
        //TODO add card to accounts
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

    public enum Status {
        STUDENT, NORMAL
    }
}
