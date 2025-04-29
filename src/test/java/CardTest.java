import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class CardTest {
    @Test
    public void testAddTrip() {
        Card card = new StudentCard();
        card.addTrips(2);
        double amount = 2 * IndividualTrips.tripPrice;

        int expectedBalance = 2;
        int result = card.getBalance();

        Transaction transaction = new Transaction(amount, new IndividualTrips());
        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(transaction);
        Stack<Transaction> resultTransaction = card.getTransactions();

        Assertions.assertEquals(expectedBalance, result);
        Assertions.assertTrue(expectedTransactions.size() == resultTransaction.size()
                              && expectedTransactions.containsAll(resultTransaction)
                              && resultTransaction.containsAll(expectedTransactions));
    }

    @Test
    public void testAddMonthly() {
        Card card = new StudentCard();
        card.addMonthly(Monthly.discountPrice);

        boolean expected = true;
        boolean result = card.isMonthly();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testAddWeekly() {
        Card card = new StudentCard();
        card.addWeekly(Weekly.discountPrice);

        boolean expected = true;
        boolean result = card.isWeekly();
        Assertions.assertEquals(expected, result);
    }

}
