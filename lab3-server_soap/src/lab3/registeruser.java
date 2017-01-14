package lab3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connections.ConnectionPoolManager;
import connections.dbConnection;

public class registeruser {
	
	public boolean newuser(String user,String password,String firstname,String lastname)throws SQLException,Exception
	{
//		ConnectionPoolManager connection=new ConnectionPoolManager();
//		Connection con=connection.getConnectionFromPool();
		
		System.out.println("1");
		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		Statement stmt=null;
		stmt=con.createStatement();
		System.out.println("2");
		String searchuser="select * from userdetails where user='"+user+"'";
		String adduser="INSERT INTO userdetails (`user`, `password`, `firstname`, `lastname`) VALUES ('"+user+"',SHA1('" +password+"'), '"+ firstname+"', '"+lastname+"')";

		try
		{
			
			System.out.println("3");
			ResultSet rs=stmt.executeQuery(searchuser);
			if(rs.next())
			{
				System.out.println("4");
				return false;
			}
			else
			{
				System.out.println("5");
				int affectedRows2=stmt.executeUpdate(adduser);
				if(affectedRows2>0)
				{
					System.out.println("6");
					return true;
				}
				else
				{
					System.out.println("7");
					return false;
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("8");
			System.out.println("inside error"+e);
			return false;
		}
	}


}
