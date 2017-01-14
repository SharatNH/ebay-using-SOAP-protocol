package lab3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.jws.WebService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.jws.WebService;

import connections.ConnectionPoolManager;
import connections.dbConnection;


@WebService
public class additem {

	
	
	public boolean additems(String user,String title,String itemdesc,String sellerdetails,int itemprice,int quantity,int bidding) throws Exception
	{
		


		dbConnection connection = new dbConnection();

		Connection con=connection.config();
		
		//first connect to db
//		ConnectionPoolManager connection=new ConnectionPoolManager();
//		Connection con=connection.getConnectionFromPool();
		
		Statement stmt=null;
		stmt=con.createStatement();
		
		String query=null;
		
		//id to be inserted for products
		int id=(int) Math.floor((Math.random() * 10000) + 1); 
		
		//timestamp
		Date date= new Date();
		@SuppressWarnings("deprecation")
		int day=date.getDate();
		
		query="INSERT INTO saleitems (`user`, `title`, `itemdesc`, `sellerinfo`,`itemprice`,`quantity`,`bidding`,`timecopy`,`id`) VALUES ('"+user+"','"+title+"', '"+itemdesc+"', '"+sellerdetails+"', '"+itemprice+"','"+quantity+"','"+bidding+"','"+day+"','"+id+"')";
		
		try
		{
			int affectedRows=stmt.executeUpdate(query);
			System.out.println("affectedRows:"+affectedRows);
			if(affectedRows>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	
		
		
		
		
	}
}
