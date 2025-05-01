import org.example.UserInputManager;
import org.junit.jupiter.api.Test;

public class UserInputManagerTest {

    @Test
    public void testDisplayMainMenu() {
        UserInputManager.displayMainMenu();
    }

    @Test
    public void testDisplayBuyMenu() {
        UserInputManager.displayBuyMenu();
    }

    @Test
    public void testDisplayProceedMenu() {
        UserInputManager.displayProceedMenu();
    }
}
