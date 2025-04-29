import org.example.Monthly;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class MonthlyTest {

    @Test
    public void testMonthlyRecharge1() {
        Monthly monthly = new Monthly();
        int year = LocalDateTime.now().getYear();
        Month month = LocalDateTime.now().plusMonths(1).getMonth();
        monthly.setPurchaseDate(LocalDateTime.of(year, month, 1, 1, 1));

        boolean expected = false;
        boolean result = monthly.recharge();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMonthlyRecharge2() {
        Monthly monthly = new Monthly();
        int year = LocalDateTime.now().getYear();
        Month month = LocalDateTime.now().getMonth();
        monthly.setPurchaseDate(LocalDateTime.of(year, month, 1, 1, 1));

        boolean expected = true;
        boolean result = monthly.recharge();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testMonthlyRecharge3() {
        Monthly monthly = new Monthly();
        int year = 2024;
        Month month = LocalDateTime.now().getMonth();
        monthly.setPurchaseDate(LocalDateTime.of(year, month, 1, 1, 1));

        boolean expected = false;
        boolean result = monthly.recharge();

        Assertions.assertEquals(expected, result);
    }
}
