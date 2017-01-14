package connections;

import java.sql.*;


public class dbConnection {
	
	public  Connection config() throws Exception{

		
		
		System.out.println("Loading driver...");

		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		
		
		String url = "jdbc:mysql://localhost:3306/ebay";
		String username = "root";
		String password = "abc123";

		System.out.println("Connecting database...");

		try  {
			Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    return connection;
		} catch (SQLException e) {
		    //throw new IllegalStateException("Cannot connect the database!", e);
			System.out.println("Cannot connect to db"+e);
			return null;
		}

	
		
	}	
	

}
