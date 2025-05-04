package org.example;

public class Main {
    public static void main(String[] args) {
        Accounts.addFromFile(Accounts.getAccountsFile());
        Card.nextId++;
        UserInputManager userInputManager = new UserInputManager();
        userInputManager.welcomeMenuOption();
    }
}
