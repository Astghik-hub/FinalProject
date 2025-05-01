import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class CardTest {
    @Test
    public void testAddTrip1() {
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
    public void testAddTrip2() {
        Card card = new StudentCard();
        card.addTrips(0);

        int expectedBalance = 0;
        int result = card.getBalance();

        Assertions.assertEquals(expectedBalance, result);

        double amount = 0 * IndividualTrip.tripPrice;
        Transaction transaction = new Transaction(amount, new IndividualTrip());
        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(transaction);
        Stack<Transaction> resultTransaction = card.getTransactions();

        Assertions.assertTrue(expectedTransactions.size() == resultTransaction.size()
                              && expectedTransactions.getFirst().getAmount() == resultTransaction.getFirst().getAmount()
                              && expectedTransactions.getFirst().getTicket().getClass() == resultTransaction.getFirst().getTicket().getClass());
    }

    @Test
    public void testAddMonthly1() {
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
    public void testAddMonthly2() {
        Card card = new NormalCard();
        card.addMonthly(Monthly.normalPrice);

        boolean expected = true;
        boolean result = card.isMonthly();
        Assertions.assertEquals(expected, result);

        Transaction transaction = new Transaction(Monthly.normalPrice, new Monthly());
        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(transaction);
        Stack<Transaction> resultTransaction = card.getTransactions();

        Assertions.assertTrue(expectedTransactions.size() == resultTransaction.size()
                              && expectedTransactions.getFirst().getAmount() == resultTransaction.getFirst().getAmount()
                              && expectedTransactions.getFirst().getTicket().getClass() == resultTransaction.getFirst().getTicket().getClass());

    }

    @Test
    public void testAddWeekly1() {
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
    public void testAddWeekly2() {
        Card card = new NormalCard();
        card.addWeekly(Weekly.normalPrice);

        boolean expected = true;
        boolean result = card.isWeekly();
        Assertions.assertEquals(expected, result);

        Transaction transaction = new Transaction(Weekly.normalPrice, new Weekly());
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
    public void testCancel_cancelMonthly1() {
        Card card = new StudentCard();

        card.addTrips(1);
        card.addTrips(1);
        card.addWeekly(Weekly.discountPrice);
        card.addMonthly(Monthly.discountPrice);

        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(card.getTransactions().get(0));
        expectedTransactions.add(card.getTransactions().get(1));
        expectedTransactions.add(card.getTransactions().get(2));

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
    public void testCancel_cancelMonthly2() {
        Card card = new StudentCard();

        card.addTrips(1);
        card.addTrips(1);
        card.addMonthly(Monthly.discountPrice);
        card.addMonthly(Monthly.discountPrice);

        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(card.getTransactions().get(0));
        expectedTransactions.add(card.getTransactions().get(1));
        expectedTransactions.add(card.getTransactions().get(2));

        card.cancel();

        Stack<Transaction> resultTransactions = card.getTransactions();
        Assertions.assertTrue(expectedTransactions.size() == resultTransactions.size()
                              && expectedTransactions.containsAll(resultTransactions)
                              && resultTransactions.containsAll(expectedTransactions));

        boolean expected = true;
        boolean result = card.isMonthly();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCancel_cancelWeekly1() {
        Card card = new StudentCard();

        card.addTrips(1);
        card.addTrips(1);
        card.addMonthly(Monthly.discountPrice);
        card.addWeekly(Weekly.discountPrice);

        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(card.getTransactions().get(0));
        expectedTransactions.add(card.getTransactions().get(1));
        expectedTransactions.add(card.getTransactions().get(2));

        card.cancel();

        Stack<Transaction> resultTransactions = card.getTransactions();
        Assertions.assertTrue(expectedTransactions.size() == resultTransactions.size()
                              && expectedTransactions.containsAll(resultTransactions)
                              && resultTransactions.containsAll(expectedTransactions));

        boolean expected = false;
        boolean result = card.isWeekly();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCancel_cancelWeekly2() {
        Card card = new StudentCard();

        card.addTrips(1);
        card.addTrips(1);
        card.addWeekly(Weekly.discountPrice);
        card.addWeekly(Weekly.discountPrice);

        Stack<Transaction> expectedTransactions = new Stack<>();
        expectedTransactions.add(card.getTransactions().get(0));
        expectedTransactions.add(card.getTransactions().get(1));
        expectedTransactions.add(card.getTransactions().get(2));

        card.cancel();

        Stack<Transaction> resultTransactions = card.getTransactions();
        Assertions.assertTrue(expectedTransactions.size() == resultTransactions.size()
                              && expectedTransactions.containsAll(resultTransactions)
                              && resultTransactions.containsAll(expectedTransactions));

        boolean expected = true;
        boolean result = card.isWeekly();
        Assertions.assertEquals(expected, result);
    }
}
