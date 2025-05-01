import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class AccountsTest {
    public static String filePath = "src/test/resources/fakeAccounts.csv";
    public static File fakeFile = new File(filePath);

    @Test
    public void testAddFromFile() throws IOException {
        Accounts.addFromFile(fakeFile);
        TreeSet<Card> resultCards = Accounts.getCards();

        TreeSet<Card> expectedCards = new TreeSet<>();
        try (Scanner scanner = new Scanner(fakeFile)) {
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
                    expectedCards.add(new StudentCard(id, status, new Owner(fname, lname), balance, isMonthly, isWeekly));
                } else if (status.equals(Card.Status.NORMAL)) {
                    expectedCards.add(new NormalCard(id, status, new Owner(fname, lname), balance, isMonthly, isWeekly));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(expectedCards.size() == resultCards.size()
                              && expectedCards.containsAll(resultCards)
                              && resultCards.containsAll(expectedCards));
    }

    @Test
    public void testFindCard1() {
        Owner o1 = new Owner("john", "rich");
        Owner o2 = new Owner("alice", "rich");
        Owner o3 = new Owner("john", "harper");
        Card c1 = new StudentCard(o1);
        Card c2 = new StudentCard(o2);
        Card c3 = new StudentCard(o3);

        TreeSet<Card> fakeCards = new TreeSet<>();
        Accounts.setCards(fakeCards);
        fakeCards.add(c1);
        fakeCards.add(c2);
        fakeCards.add(c3);

        int id = 2;
        Card expected = c2;
        Card result = Accounts.findCard(id);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testFindCard2() {
        Owner o1 = new Owner("john", "rich");
        Owner o2 = new Owner("alice", "rich");
        Owner o3 = new Owner("john", "harper");
        Card c1 = new StudentCard(o1);
        Card c2 = new StudentCard(o2);
        Card c3 = new StudentCard(o3);

        TreeSet<Card> fakeCards = new TreeSet<>();
        Accounts.setCards(fakeCards);
        fakeCards.add(c1);
        fakeCards.add(c2);
        fakeCards.add(c3);

        int id = 35;
        Card expected = null;
        Card result = Accounts.findCard(id);

        Assertions.assertEquals(expected, result);
    }
}
