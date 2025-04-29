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
                              && expectedTransactions.peek().getAmount() == resultTransaction.peek().getAmount()
                              && expectedTransactions.peek().getTicket().getClass() == resultTransaction.peek().getTicket().getClass());

    }

    @Test
    public void testCancel_cancelTrip() {
        Card card = new StudentCard();
        card.setBalance(3);

        Transaction t1 = new Transaction(IndividualTrip.tripPrice, new IndividualTrip());
        Transaction t2 = new Transaction(IndividualTrip.tripPrice, new IndividualTrip());
        Transaction t3 = new Transaction(Monthly.discountPrice, new Monthly());
        Transaction t4 = new Transaction(IndividualTrip.tripPrice, new IndividualTrip());
        card.getTransactions().add(t1);
        card.getTransactions().add(t2);
        card.getTransactions().add(t3);
        card.getTransactions().add(t4);

        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(t1);
        expectedTransactions.add(t2);
        expectedTransactions.add(t3);

        card.cancel();

        Stack<Transaction> resultTransactions = card.getTransactions();
        Assertions.assertTrue(expectedTransactions.size() == resultTransactions.size()
                              && expectedTransactions.containsAll(resultTransactions)
                              && resultTransactions.containsAll(expectedTransactions));

        int expectedBalance = 2;
        int resultBalance = card.getBalance();
        Assertions.assertEquals(expectedBalance, resultBalance);
    }

    @Test
    public void testCancel_cancelMonthly() {
        Card card = new StudentCard();

        Transaction t1 = new Transaction(IndividualTrip.tripPrice, new IndividualTrip());
        Transaction t2 = new Transaction(IndividualTrip.tripPrice, new IndividualTrip());
        Transaction t3 = new Transaction(Weekly.discountPrice, new Weekly());
        Transaction t4 = new Transaction(Monthly.discountPrice, new Monthly());
        card.getTransactions().add(t1);
        card.getTransactions().add(t2);
        card.getTransactions().add(t3);
        card.getTransactions().add(t4);

        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(t1);
        expectedTransactions.add(t2);
        expectedTransactions.add(t3);

        card.cancel();

        Stack<Transaction> resultTransactions = card.getTransactions();
        Assertions.assertTrue(expectedTransactions.size() == resultTransactions.size()
                              && expectedTransactions.containsAll(resultTransactions)
                              && resultTransactions.containsAll(expectedTransactions));

        boolean expected = false;
        boolean result = card.isMonthly();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCancel_cancelWeekly() {
        Card card = new StudentCard();

        Transaction t1 = new Transaction(IndividualTrip.tripPrice, new IndividualTrip());
        Transaction t2 = new Transaction(IndividualTrip.tripPrice, new IndividualTrip());
        Transaction t3 = new Transaction(Monthly.discountPrice, new Monthly());
        Transaction t4 = new Transaction(Weekly.discountPrice, new Weekly());
        card.getTransactions().add(t1);
        card.getTransactions().add(t2);
        card.getTransactions().add(t3);
        card.getTransactions().add(t4);

        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(t1);
        expectedTransactions.add(t2);
        expectedTransactions.add(t3);

        card.cancel();

        Stack<Transaction> resultTransactions = card.getTransactions();
        Assertions.assertTrue(expectedTransactions.size() == resultTransactions.size()
                              && expectedTransactions.containsAll(resultTransactions)
                              && resultTransactions.containsAll(expectedTransactions));

        boolean expected = false;
        boolean result = card.isWeekly();
        Assertions.assertEquals(expected, result);
    }
}
