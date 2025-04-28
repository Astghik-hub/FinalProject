import org.example.Card;
import org.example.IndividualTrips;
import org.example.StudentCard;
import org.example.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    public void testAddTrip() {
        Card card = new StudentCard();
        card.addTrips(2);
        double amount = 2 * Card.tripPrice;
        Transaction transaction = new Transaction(amount, new IndividualTrips());
        int expectedBalance = 2;
        int result = card.getBalance();

        Assertions.assertEquals(expectedBalance, result);
    }

    @Test
    public void testAddMonthly() {
        Card card = new StudentCard();
        card.addMonthly(Card.monthlyDiscountPrice);

        boolean expected = true;
        boolean result = card.isMonthly();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testAddWeekly() {
        Card card = new StudentCard();
        card.addWeekly(Card.weeklyDiscountPrice);

        boolean expected = true;
        boolean result = card.isWeekly();
        Assertions.assertEquals(expected, result);
    }

}
