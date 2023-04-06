import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLDatabaseConnection {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
	
	public static String connect(String prompt, Command type) {
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
		 		if(type.equals(Command.LOGIN) || type.equals(Command.CHECKB)) {
		 			String command = null;
		 			if(type.equals(Command.LOGIN)) {
		 				command = "Select Username, PassWord from dbo.Login where Username like \'" + prompt +"\'";
		 			}
		 			else if(type.equals(Command.CHECKB)) {
		 				command = "Select id, Balance, Type from dbo.Accounts " + prompt;
		 			}
		 			try (Connection connection = DriverManager.getConnection(connectionUrl);) {
		 				Statement statement = connection.createStatement(); {
		 					String selectSQl = command;
		 					resultSet = statement.executeQuery(selectSQl);
		 					while(resultSet.next()) {
		 						returnedVals.add(resultSet.getString(2));
		 					}
		 					return returnedVals.get(0);
		         		
		 				}
		 			}
		 			// Handle any errors that may have occurred.
		 			catch (SQLException e) {
		             e.printStackTrace();
		 			}
		 		}
		 		if(returnedVals.isEmpty()) {
		 			return "Failed";
		 		}
		 		else return returnedVals.get(0);
	}
	
	public static boolean matchUserLogin(String userName, String PassWord) {
		Command getUserPass = Command.LOGIN;
		String foundPass = connect(userName, getUserPass);
		if(foundPass.equals(PassWord)) return true;
		else return false;
		
	}
	
	public static double getUserBalance(String userName, String accountType) {
		String prompt = "WHERE id like \'" + userName + "\' "+ "AND  Type like \'" + accountType + "\'";
		Command getBalance = Command.CHECKB;
		double balance = Double.parseDouble(connect(prompt, getBalance));
		return balance;
	}
	
	
    public static void main(String[] args) {
    	//System.out.println(matchUserLogin("clar0126","Hoverboard1456!"));
    	System.out.println(getUserBalance("clar0126","Checking"));
    }
}
