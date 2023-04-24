import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class SQLDatabaseConnectionTest {

    private static final String USERNAME = "clar0126";
    private static final String PASSWORD = "Hoverboard1456!";

    @Test
    public void testMatchUserLogin() {
        boolean expected = true;
        boolean actual = SQLDatabaseConnection.matchUserLogin(USERNAME, PASSWORD);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetUserBalance() {
        double expected = 1000.0;
        double actual = SQLDatabaseConnection.getUserBalance(USERNAME, "Checking");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAccountTypes() {
        String[] expected = {"Checking", "Savings"};
        String[] actual = SQLDatabaseConnection.getAccountTypes(USERNAME);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testDeposit() {
        double expected = 1100.0;
        SQLDatabaseConnection.deposit(USERNAME, "Checking", 100.0);
        double actual = SQLDatabaseConnection.getUserBalance(USERNAME, "Checking");
        Assertions.assertEquals(expected, actual);
    }
}
