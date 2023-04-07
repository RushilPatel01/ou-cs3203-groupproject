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
		 		else if(type.equals(Command.UPDATEB)) {
		 			String updateSql = prompt;
		 			try(Connection connection = DriverManager.getConnection(connectionUrl); PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);){
		 				prepsUpdateProduct.execute();
		 				resultSet = prepsUpdateProduct.getGeneratedKeys();
		 				while(resultSet.next()) {
		 					System.out.println(resultSet.getString(1));
		 				}
		 			}
		 			catch (Exception e) {
		 	            e.printStackTrace();
		 			}
		 	        
		 		}
		 		if(returnedVals.isEmpty()) {
		 			return null;
		 		}
		 		else return returnedVals;
	}
	
	public static boolean matchUserLogin(String userName, String PassWord) {
		Command getUserPass = Command.LOGIN;
		String foundPass = connect(userName, getUserPass).get(0);
		if(foundPass.equals(PassWord)) return true;
		else return false;
		
	}
	
	public static double getUserBalance(String userName, String accountType) {
		String prompt = "WHERE id like \'" + userName + "\' "+ "AND  Type like \'" + accountType + "\'";
		Command getBalance = Command.CHECKB;
		double balance = Double.parseDouble(connect(prompt, getBalance).get(0));
		return balance;
	}
	
	public static String[] getAccountTypes(String userName) {
		Command getExistingAccounts = Command.ACCOUNTDISP;
		ArrayList<String> AvailableAccounts = connect(userName,getExistingAccounts);
		String[] foundTypes = new String[2];
		for(int i = 0; i < AvailableAccounts.size(); ++i) {
			foundTypes[i] = AvailableAccounts.get(i);
		}
		return foundTypes;
	}
	
	public static void deposit(String userName, String accountType,  double deposit ) {
		double currentBalance = getUserBalance(userName, accountType);
		double newBalance = currentBalance + deposit;
		String convertedBal = Double.toString(newBalance);
		String updateBank = "Update dbo.Accounts " + "set Balance = " + convertedBal + "where id like \'" + userName + "\'" + "AND Type like \'" + accountType + "\' " ;
		Command update = Command.UPDATEB;
		connect(updateBank, update);
		System.out.println(getUserBalance(userName,accountType));
	}
	
	
    public static void main(String[] args) {
    	//System.out.println(matchUserLogin("clar0126","Hoverboard1456!"));
    	deposit("clar0126", "Checking", 400);
    	
    }
}
