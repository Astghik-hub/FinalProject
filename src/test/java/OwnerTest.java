import org.example.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class OwnerTest {

    @Test
    public void testToTitleCase1() {
        String fname = "john";
        String lname = "rich";
        Owner owner = new Owner(fname, lname);

        String expectedFname = "John";
        String expectedLname = "Rich";
        String resultFname = owner.getFname();
        String resultLname = owner.getLname();

        Assertions.assertEquals(expectedFname, resultFname);
        Assertions.assertEquals(expectedLname, resultLname);
    }

    @Test
    public void testToTitleCase2() {
        String fname = "joHn";
        String lname = "ricH";
        Owner owner = new Owner(fname, lname);

        String expectedFname = "John";
        String expectedLname = "Rich";
        String resultFname = owner.getFname();
        String resultLname = owner.getLname();

        Assertions.assertEquals(expectedFname, resultFname);
        Assertions.assertEquals(expectedLname, resultLname);
    }

    @Test
    public void testToTitleCase3() {
        String fname = " ";
        String lname = " ";
        Owner owner = new Owner(fname, lname);

        String expectedFname = " ";
        String expectedLname = " ";
        String resultFname = owner.getFname();
        String resultLname = owner.getLname();

        Assertions.assertEquals(expectedFname, resultFname);
        Assertions.assertEquals(expectedLname, resultLname);
    }

    @Test
    public void testToTitleCase4() {
        String fname = "";
        String lname = "";
        Owner owner = new Owner(fname, lname);

        String expectedFname = "";
        String expectedLname = "";
        String resultFname = owner.getFname();
        String resultLname = owner.getLname();

        Assertions.assertEquals(expectedFname, resultFname);
        Assertions.assertEquals(expectedLname, resultLname);
    }
}
