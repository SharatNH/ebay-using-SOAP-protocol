package lab3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import connections.ConnectionPoolManager;
import connections.cartitems;
import connections.dbConnection;
import connections.saleitems;

@WebService
public class shoppingcart {
	
	
	public String listshoppingcart(String user) throws Exception
	{
		System.out.println("1");
		String listItems;
//		ConnectionPoolManager connection = new ConnectionPoolManager();
//		Connection conn = connection.getConnectionFromPool();
		
		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		
		System.out.println("2");
		JsonObject resultSet = new JsonObject();
		Statement stmt = null;
		stmt=con.createStatement();
		int count=0;

		List<cartitems> listinfo = new ArrayList<cartitems>();
		
		listItems = "select * from cart where user='" +user+"'";
		
		try
		{
			System.out.println("3");
			ResultSet rs=stmt.executeQuery(listItems);
			while(rs.next())
			{
				System.out.println("4");
				count++;
				//String users=rs.getString(1);
				String title=rs.getString(2);
				String itemdesc=rs.getString(3);
				
				int quantity=rs.getInt(4);
				int rate=rs.getInt(5);
				int id=rs.getInt(6);
				int total=rs.getInt(7);
				//rate id total
				
				cartitems item=new cartitems(id,title,itemdesc,rate,quantity,total);
				listinfo.add(item);
			
							
			}
			if(count>0)
			{
				 Gson gson = new Gson();
					String json = gson.toJson(listinfo);
					System.out.println("Json string:"+json);
					 con.close();
					 return json;
					 
					 
			}
			else
			{
				System.out.println("6");
				String result="false";
				return result;	
			}
		}
		catch(SQLException e)
		{
			System.out.println("7");
			System.out.println("error"+e);
			String result="false";
			return result;
		}
			
	}
	
	
	
	//delete cart
	
	public boolean deletecart(String user,int pid)throws Exception
	{
		
//		ConnectionPoolManager connection=new ConnectionPoolManager();
//		Connection con=connection.getConnectionFromPool();
	
		System.out.println("1");
		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		Statement stmt=null;
		stmt=con.createStatement();
		
		String cartdelete; 
		
		try
		{
			System.out.println("2");
			cartdelete = "delete from cart where id ='"+pid+"' and user='"+user+"'";
			int affectedRows=stmt.executeUpdate(cartdelete);
			if(affectedRows>0)
			{
				System.out.println("3");
				return true;
			}
			else
			{
				System.out.println("4");
				return false;
			}
		}
		catch(SQLException e)
		{
			System.out.println("5");
			return false;
		}
	
			
		
	}

}
