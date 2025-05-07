import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.example.Card.Status.STUDENT;

public class AccountsTest {
    public static String filePath = "src/test/resources/fakeAccounts.csv";
    public static File fakeFile = new File(filePath);

    @Test
    public void testAddFromFile() throws IOException {
        Owner o1 = new Owner("astghik", "minasyan");
        Owner o2 = new Owner("arpine", "grigoryan");
        Owner o3 = new Owner("siranush", "minasyan");
        Card c1 = new StudentCard(1, STUDENT, o1,0, new Monthly(), true, new Weekly(),false);
        Card c2 = new StudentCard(2, STUDENT, o2,0, new Monthly(), false, new Weekly(), true);
        Card c3 = new StudentCard(3, STUDENT, o3, 0, new Monthly(), false, new Weekly(), false);

        TreeSet<Card> fakeCards = new TreeSet<>();
        fakeCards.add(c1);
        fakeCards.add(c2);
        fakeCards.add(c3);
        TreeSet<Card> expectedCards = fakeCards;

        Accounts.setCards(fakeCards);
        Accounts.addFromFile(fakeFile);
        TreeSet<Card> resultCards = Accounts.getCards();
        Assertions.assertTrue(expectedCards.size() == resultCards.size()
                              && expectedCards.containsAll(resultCards)
                              && resultCards.containsAll(expectedCards));
    }

    @Test
    public void testFindCard1() {
        Accounts.addFromFile(fakeFile);
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
