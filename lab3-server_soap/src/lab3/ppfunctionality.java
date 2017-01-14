package lab3;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import connections.dbConnection;

import javax.jws.WebService;

import org.json.JSONException;

import com.google.gson.JsonObject;

import connections.ConnectionPoolManager;

@WebService
public class ppfunctionality {
	
	public boolean addtocart(String user,String title,String itemdesc,int pid,int rate,int quantity) throws Exception
	{
		
		System.out.println("1");
		String cartitem=null;
		int total=rate*quantity;
		
//		ConnectionPoolManager connection=new ConnectionPoolManager();
//		Connection con=connection.getConnectionFromPool();

		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		Statement stmt=null;
		stmt=con.createStatement();
		System.out.println("2");
		
		cartitem="INSERT INTO cart (`user`, `title`, `itemdesc`, `quantity`,`rate`,`id`,`total`) VALUES ('"+user+"','"+title+"', '"+itemdesc+"', '"+quantity+"', '"+rate+"','"+pid+"','"+total+"')";
		
		try
		{
			System.out.println("3");
			int affectedRows=stmt.executeUpdate(cartitem);
			
			System.out.println(affectedRows);
			if(affectedRows>0)
			{
				System.out.println("4");
				System.out.println("inside true afrows");
				return true;
			}
			else
			{
				System.out.println("5");
				System.out.println("inside false afrows");
				return false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("6");
			System.out.println("in error"+e);
			return false;
		}
		
	}
	
	
	public boolean addtobid(String user,int pid,int quantity,int bidamt)throws Exception
	{
//		ConnectionPoolManager connection=new ConnectionPoolManager();
//		Connection con=connection.getConnectionFromPool();
		
		System.out.println("inside addtobid");
		System.out.println("1");
		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		
		Statement stmt=null;
		stmt=con.createStatement();
		String addtobid=null;
		
		Date timestamp=new Date();
		System.out.println("2");
		addtobid="INSERT INTO addbid (`user`, `pid`, `timestamp`, `amt`,`qty`) VALUES ('"+user+"','"+pid+"', '"+timestamp+"', '"+bidamt+"', '"+quantity+"')";
		
		try
		{
			int affectedRows=stmt.executeUpdate(addtobid);
			System.out.println(affectedRows);
			if(affectedRows>0)
			{
				System.out.println("3");
				System.out.println("affected rows >1");
				return true;
			}
			else
			{
				System.out.println("4");
				System.out.println("affected rows <1");
				return false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("5");
			System.out.println("inside error"+e);
			return false;
		}
		
		
	}
	
	
	public String getcart(int pid) throws Exception
	{
//		ConnectionPoolManager connection=new ConnectionPoolManager();
//		Connection con=connection.getConnectionFromPool();
		
		System.out.println("1");
		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		
		int count=0;
		
		Statement stmt=null;
		stmt=con.createStatement();
		System.out.println("2");
		String getcart=null;
		JsonObject resultSet=new JsonObject();
		System.out.println("3");
		getcart="select * from saleitems where id='" +pid+"'";
		
		try
		{
			System.out.println("4");
			ResultSet rs = stmt.executeQuery(getcart);
			while(rs.next())
			{
				count++;
				System.out.println("5");
				resultSet.addProperty("title", rs.getString(2));
				 resultSet.addProperty("itemdesc", rs.getString(3));
				 resultSet.addProperty("sellerinfo", rs.getString(4));
				 resultSet.addProperty("itemprice", rs.getString(5));
				 resultSet.addProperty("quantity", rs.getString(6));
				 resultSet.addProperty("bidding", rs.getString(7));
				 resultSet.addProperty("timecopy", rs.getString(8));
				 resultSet.addProperty("id", rs.getString(9));
				
			}
			if(count>0)
			{
				System.out.println(resultSet.toString());
				return resultSet.toString();
				
			}
			else
			{
				String result="false";
				return result;
			}
		
		}
		catch(SQLException e)
		{
			System.out.println("6");
			System.out.println("error"+e);
			String result="error";
			return result;
			
		}
		
	}
	

}
