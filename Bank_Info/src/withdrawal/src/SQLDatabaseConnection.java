import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;

public class SQLDatabaseConnection {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
	
	/*
	 * input: prompt that specifies action to be taken by database, type Enum control of action
	 * output: ArrayList that holds all desired database query info
	 * function: connects to database using JDBC and executes desired action
	 */
	public static ArrayList<String> connect(String prompt, Command type) {
		 String connectionUrl =
			        "jdbc:sqlserver://bankmainproject.database.windows.net:1433;" 
				+"database=Bank_Main;" 
			    + "user=clar0126@bankmainproject;"
				+ "password=Hoverboard1456!;" 
			    + "encrypt=true;" 
				+ "trustServerCertificate=false;" 
			    + "hostNameInCertificate=*.database.windows.net;" 
				+ "loginTimeout=30;";
		 		ArrayList<String> returnedVals = new ArrayList<String>();
		 		ResultSet resultSet = null;
		 		if(type.equals(Command.LOGIN) || type.equals(Command.CHECKB) || type.equals(Command.ACCOUNTDISP)) {
		 			String command = null;
		 			if(type.equals(Command.LOGIN)) {
		 				command = "Select Username, PassWord from dbo.Login where Username like \'" + prompt +"\'";
		 			}
		 			else if(type.equals(Command.CHECKB)) {
		 				command = "Select id, Balance, Type from dbo.Accounts " + prompt;
		 			}
		 			else if (type.equals(Command.ACCOUNTDISP)) {
		 				command = "Select id, Type, Balance from dbo.Accounts where id like \'" + prompt + "\'";
		 			}
		 			else if (type.equals(Command.UPDATEB)) {
		 				command = prompt;
		 			}
		 			try (Connection connection = DriverManager.getConnection(connectionUrl);) {
		 				Statement statement = connection.createStatement(); {
		 					String selectSQl = command;
		 					resultSet = statement.executeQuery(selectSQl);
		 					while(resultSet.next()) {
		 						returnedVals.add(resultSet.getString(2));
		 					}
		 					return returnedVals;
		         		
		 				}
		 			}
		 			// Handle any errors that may have occurred.
		 			catch (SQLException e) {
		             e.printStackTrace();
		 			}
		 		}
		 		else if(type.equals(Command.UPDATEB)) { //only scrip that actually changes database
		 			String updateSql = prompt;
		 			try(Connection connection = DriverManager.getConnection(connectionUrl); PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);){
		 				prepsUpdateProduct.execute();
		 				resultSet = prepsUpdateProduct.getGeneratedKeys();
		 				
		 			}
		 			catch (Exception e) {
		 	            e.printStackTrace();
		 			}
		 	        
		 		}
		 		if(returnedVals.isEmpty()) {
		 			return null;
		 		}
		 		else {
					return returnedVals;
				}
	}
	/*
	 * input: userName (username as in database), password (the users attempted sign on password)
	 * output: boolean determining if successful login (matching database info with user input)
	 * function: determines if a user's login information matches a database record
	 */
	public static boolean matchUserLogin(String userName, String PassWord) {
		Command getUserPass = Command.LOGIN;
		String foundPass = connect(userName, getUserPass).get(0);
		if(foundPass.equals(PassWord)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/*
	 * input: username for account info, accountType string (either Checking or Savings) determining account selection
	 * output: double returning user's current account balance
	 * function: gets a users current bank balance based on specified account type
	 */
	public static double getUserBalance(String userName, String accountType) {
		String prompt = "WHERE id like \'" + userName + "\' "+ "AND  Type like \'" + accountType + "\'";
		Command getBalance = Command.CHECKB;
		double balance = Double.parseDouble(connect(prompt, getBalance).get(0));
		return balance;
	}
	
	/*
	 * input: username for account info
	 * output: string array holding options of current user's accounts for UI display
	 * function: can be linked with the UI to display a drop down menu of user's current account types
	 */
	public static String[] getAccountTypes(String userName) {
		Command getExistingAccounts = Command.ACCOUNTDISP;
		ArrayList<String> AvailableAccounts = connect(userName,getExistingAccounts);
		String[] foundTypes = new String[2];
		for(int i = 0; i < AvailableAccounts.size(); ++i) {
			foundTypes[i] = AvailableAccounts.get(i);
		}
		return foundTypes;
	}
	/*
	 * input: username for account info, accountType for specified account, deposit for transaction amount
	 * output: none
	 * function: deposits money into a user's account in database record
	 */
	public static void deposit(String userName, String accountType,  double deposit ) {
		double currentBalance = getUserBalance(userName, accountType);
		double newBalance = currentBalance + deposit;
		String convertedBal = Double.toString(newBalance);
		String updateBank = "Update dbo.Accounts " + "set Balance = " + convertedBal + "where id like \'" + userName + "\'" + "AND Type like \'" + accountType + "\' " ;
		Command update = Command.UPDATEB;
		connect(updateBank, update);
	}
	
	/*
	 * input: username for account info, accountType for specified account, withdraw for money to be taken from account
	 * output: boolean determining if the user had sufficient funds for withdraw
	 * function: takes money from user's account if available
	 */
	public static boolean withdrawal(String userName, String accountType, double withdraw) {
		double currentBalance = getUserBalance(userName, accountType);
		boolean sufficientFunds = false;
		if(currentBalance >= withdraw) {
			deposit(userName, accountType, -withdraw);
			sufficientFunds = true;
		}
		else {
			//insufficient funds message to front display
			sufficientFunds = false;
		}
		return sufficientFunds;
	}
	
	
    public static void main(String[] args) {
    	//System.out.println(matchUserLogin("clar0126","ClassTime123!"));
    	//deposit("clar0126", "Checking", 400);
    	System.out.println(withdrawal("clar0126", "Checking", 9000));
    	//System.out.println(getUserBalance("clar0126", "Checking"));
    }
}
