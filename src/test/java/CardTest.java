import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class CardTest {
    @Test
    public void testAddTrip() {
        Card card = new StudentCard();
        card.addTrips(2);

        int expectedBalance = 2;
        int result = card.getBalance();

        Assertions.assertEquals(expectedBalance, result);

        double amount = 2 * IndividualTrip.tripPrice;
        Transaction transaction = new Transaction(amount, new IndividualTrip());
        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(transaction);
        Stack<Transaction> resultTransaction = card.getTransactions();

        Assertions.assertTrue(expectedTransactions.size() == resultTransaction.size()
                              && expectedTransactions.getFirst().getAmount() == resultTransaction.getFirst().getAmount()
                              && expectedTransactions.getFirst().getTicket().getClass() == resultTransaction.getFirst().getTicket().getClass());
    }

    @Test
    public void testAddMonthly() {
        Card card = new StudentCard();
        card.addMonthly(Monthly.discountPrice);

        boolean expected = true;
        boolean result = card.isMonthly();
        Assertions.assertEquals(expected, result);

        Transaction transaction = new Transaction(Monthly.discountPrice, new Monthly());
        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(transaction);
        Stack<Transaction> resultTransaction = card.getTransactions();

        Assertions.assertTrue(expectedTransactions.size() == resultTransaction.size()
                              && expectedTransactions.getFirst().getAmount() == resultTransaction.getFirst().getAmount()
                              && expectedTransactions.getFirst().getTicket().getClass() == resultTransaction.getFirst().getTicket().getClass());

    }

    @Test
    public void testAddWeekly() {
        Card card = new StudentCard();
        card.addWeekly(Weekly.discountPrice);

        boolean expected = true;
        boolean result = card.isWeekly();
        Assertions.assertEquals(expected, result);

        Transaction transaction = new Transaction(Weekly.discountPrice, new Weekly());
        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(transaction);
        Stack<Transaction> resultTransaction = card.getTransactions();

        Assertions.assertTrue(expectedTransactions.size() == resultTransaction.size()
                              && expectedTransactions.getFirst().getAmount() == resultTransaction.getFirst().getAmount()
                              && expectedTransactions.getFirst().getTicket().getClass() == resultTransaction.getFirst().getTicket().getClass());

    }

    @Test
    public void testCancel() {

    }
}
