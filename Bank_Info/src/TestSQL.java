import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestSQL {
	   private static final String USERNAME = "clar0126";
	   private static final String PASSWORD = "ClassTime123!";

	    @Test
	    public void testMatchUserLogin() {
	        boolean expected = true;
	        boolean actual = SQLDatabaseConnection.matchUserLogin(USERNAME, PASSWORD);
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
	        double expected = SQLDatabaseConnection.getUserBalance(USERNAME, "Checking") + 100.0;
	        SQLDatabaseConnection.deposit(USERNAME, "Checking", 100.0);
	        double actual = SQLDatabaseConnection.getUserBalance(USERNAME, "Checking");
	        Assertions.assertEquals(expected, actual);
	    }
	    @Test
	    public void testWithdraw() {
	        // Test withdraw
	        double initialBalance = SQLDatabaseConnection.getUserBalance(USERNAME, "Checking");
	        SQLDatabaseConnection.withdrawal(USERNAME, "Checking", 100.0);
	        double newBalance = SQLDatabaseConnection.getUserBalance(USERNAME, "Checking");
	        assertEquals(initialBalance - 100.0, newBalance, 0.001);
	        }
	    @Test
	    public void testOverWithdraw() {
	        // Test insufficient funds
	        double initialBalance = SQLDatabaseConnection.getUserBalance(USERNAME, "Checking");
	        assertFalse(SQLDatabaseConnection.withdrawal(USERNAME, "Checking", initialBalance + 1000000000.0));
	    } 
	    
	    @Test
	    public void testMatchUserLoginBadPassword() {
	        boolean expected = true;
	        boolean actual = SQLDatabaseConnection.matchUserLogin(USERNAME, "wrong");
	        Assertions.assertEquals(false, actual);
	    }
	    
	    @Test
	    public void testMatchUserLoginBadUser() {
	        boolean expected = true;
	        boolean thrown = false;
	        try {
	        	SQLDatabaseConnection.matchUserLogin("alsowrong", "wrong");
	          } catch (IndexOutOfBoundsException e) {
	            thrown = true;
	          }

	          assertTrue(thrown);
	        }
	    

}
