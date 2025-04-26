package org.example;

import java.util.Map;

public class UserInputManager {
    public static Map<Integer, String> proceedMenu;

    public static void displayProceedMenu() {
        proceedMenu.put(1, "Proceed");
        proceedMenu.put(2, "Cancel");

        for (int i = 1; i <= proceedMenu.size(); i++) {
            System.out.printf("[ %d ] %-10s", i, proceedMenu.get(i));
        }
    }
}
