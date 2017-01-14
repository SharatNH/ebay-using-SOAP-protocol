package lab3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jws.WebService;

import connections.ConnectionPoolManager;
import connections.dbConnection;

@WebService
public class checklogin {
	
	
	public boolean validlogin(String user,String password) throws SQLException,Exception
	{
		System.out.println("username and password"+user+" "+password);
		
		//get connections
		System.out.println("1");
		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		System.out.println("2");
		Statement stmt=null;
		stmt=con.createStatement();
		
		System.out.println("3");
		//declare variables
		int affectedRows,count=0;
		String query=null;
		Boolean state=false;
		
		query = "SELECT * FROM userdetails WHERE password = SHA1('"+password+"') AND user='" +user+ "'";
		
		System.out.println("4");
		ResultSet rs=stmt.executeQuery(query);
		System.out.println("5");
		try
		{
			System.out.println("6");
		while(rs.next())
		{
			System.out.println("7");
			state=true;
			if(count>1)
			{
				state=false;
			}
			count=count+1;
		}
		
		return state;
		}
		catch(SQLException e)
		{
			System.out.println("8");
			System.out.println("error"+e);
			return state;
		}

		
	}

	
}
