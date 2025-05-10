import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CardTest {
    public static File fakeIdFile1 = new File("src/test/resources/fakeId1.txt");
    public static File fakeIdFile2 = new File("src/test/resources/fakeId2.txt");
    public static File fakeIdFile3 = new File("src/test/resources/fakeId3.txt");

    @Test
    public void testReadIdFromFile1() {
        int expected = 1;
        int result = Card.readIdFromFile(fakeIdFile1);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testReadIdFromFile2() {
        int expected = 1;
        int result = Card.readIdFromFile(fakeIdFile2);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testReadIdFromFile3() {
        int expected = 34;
        int result = Card.readIdFromFile(fakeIdFile3);
        Assertions.assertEquals(expected, result);
    }
}
