import org.example.Accounts;
import org.example.Card;
import org.example.Owner;
import org.example.StudentCard;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class AccountTest {

    @Test
    public void testWriteToFile() {
        Owner owner = new Owner("astgh", "min");
        Card card = new StudentCard(owner);
        String path = "src/test/resources/fakeAccounts.csv";

        Accounts.writeToFile(card, path);

        TreeSet<Card> cards = new TreeSet<>();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");
                int id = Integer.parseInt(elements[0]);
                Card.Status status = Card.Status.valueOf(elements[1]);
                String fname = elements[2];
                String lname = elements[3];
                int balance = Integer.parseInt(elements[4]);
                boolean isMonthly = Boolean.parseBoolean(elements[5]);
                boolean isWeekly= Boolean.parseBoolean(elements[6]);

                if (status.equals(Card.Status.STUDENT)) {
                    cards.add(new StudentCard(id, status, new Owner(fname, lname), balance, isMonthly, isWeekly));

                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
