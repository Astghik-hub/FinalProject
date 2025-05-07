package org.example;

public class Main {
    public static void main(String[] args) {
        Accounts.addFromFile(Accounts.getAccountsFile());
        UserInputManager userInputManager = new UserInputManager();
        userInputManager.welcomeMenuOption();
    }
}
