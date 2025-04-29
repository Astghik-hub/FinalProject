import org.example.Weekly;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class WeeklyTest {

    @Test
    public void testWeeklyRecharge1() {
        Weekly weekly = new Weekly();
        int year = LocalDateTime.now().getYear();
        Month month = LocalDateTime.now().getMonth();
        int day = LocalDateTime.now().plusWeeks(1).getDayOfMonth();
        weekly.setPurchaseDate(LocalDateTime.of(year, month, day, 1, 1));

        boolean expected = false;
        boolean result = weekly.recharge();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testWeeklyRecharge2() {
        Weekly weekly = new Weekly();
        int year = LocalDateTime.now().getYear();
        Month month = LocalDateTime.now().getMonth();
        int day = LocalDateTime.now().getDayOfMonth();
        weekly.setPurchaseDate(LocalDateTime.of(year, month, day, 1, 1));

        boolean expected = true;
        boolean result = weekly.recharge();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testWeeklyRecharge3() {
        Weekly weekly = new Weekly();
        int year = LocalDateTime.now().getYear();
        Month month = LocalDateTime.now().getMonth();
        int day = LocalDateTime.now().minusDays(18).getDayOfMonth();
        weekly.setPurchaseDate(LocalDateTime.of(year, month, day, 1, 1));

        boolean expected = false;
        boolean result = weekly.recharge();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testWeeklyRecharge4() {
        Weekly weekly = new Weekly();
        int year = LocalDateTime.now().getYear();
        Month month = LocalDateTime.now().getMonth();
        int day = LocalDateTime.now().minusDays(6).getDayOfMonth();
        weekly.setPurchaseDate(LocalDateTime.of(year, month, day, 1, 1));

        boolean expected = true;
        boolean result = weekly.recharge();
        Assertions.assertEquals(expected, result);
    }
}
