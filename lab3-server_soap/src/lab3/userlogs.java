package lab3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import connections.ConnectionPoolManager;
import connections.dbConnection;

public class userlogs {
	
	public boolean userlog(String user,double pid) throws Exception
	{
		
		//timestamp on the products clicked
		Date timestamp=new Date();
		
		//get db connection
//		ConnectionPoolManager connection=new ConnectionPoolManager();
//		Connection con=connection.getConnectionFromPool();
	
		System.out.println("1");
		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		
		//query and result set
		int Rowsaffected=0;
		String query=null;
		
		//statement
		Statement stmt=null;
		stmt=con.createStatement();
		
		//insert in db
		System.out.println("2");
		query="INSERT INTO userlogs (`user`, `pid`, `timestamp`) VALUES ('"+user+"','"+pid+"', '"+timestamp+"')";
		
		try
		{
			Rowsaffected=stmt.executeUpdate(query);
			if(Rowsaffected>0)
			{
				System.out.println("3");
				System.out.println("inside Rows...1");
				return true;
			}
			else
			{
				System.out.println("4");
				System.out.println("inside Rows...0");
				return false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("5");
			System.out.println(e);
			return false;
		}
		
		
	}

}
