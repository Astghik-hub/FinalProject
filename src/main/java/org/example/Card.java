package org.example;

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

    protected static int nextId = 0;

    public Card() {
        this.owner = new Owner("fname", "lname");
        this.status = null;
        this.balance = 0;
        this.monthly = new Monthly();
        this.isMonthly = false;
        this.weekly = new Weekly();
        this.isWeekly = false;
        this.transactions = new Stack<>();
        this.id = nextId++;
    }

    public Card(Owner owner) {
        this.owner = owner;
        this.balance = 0;
        this.monthly = new Monthly();
        this.isMonthly = false;
        this.weekly = new Weekly();
        this.isWeekly = false;
        this.transactions = new Stack<>();
        this.id = nextId++;
    }

    public Card(int id, Status status, Owner owner, int balance, boolean isMonthly, boolean isWeekly) {
        this.id = id;
        this.status = status;
        this.owner = owner;
        this.balance = balance;
        this.monthly = new Monthly();
        this.isMonthly = isMonthly;
        this.weekly = new Weekly();
        this.isWeekly = isWeekly;
        this.transactions = new Stack<>();
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
    public void cancel() {
        Transaction transaction = transactions.peek();
        if (transaction.getTicket() instanceof IndividualTrip) {
            balance -= (int) (transaction.getAmount() / transaction.getTicket().price);
        }

        if (transaction.getTicket() instanceof Monthly) {
            if (transactions.get(transactions.size() - 2).getTicket() instanceof Monthly) {
                transactions.pop();
                return;
            } else isMonthly = false;
        }

        if (transaction.getTicket() instanceof Weekly) {
            if (transactions.get(transactions.size() - 2).getTicket() instanceof Weekly) {
                transactions.pop();
                return;
            } else isWeekly = false;
        }

        transactions.pop();
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

    public static class CardComparator implements Comparator<Card> {
        private SortType sortType;

        public CardComparator(SortType sortType) {
            this.sortType = sortType;
        }

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
