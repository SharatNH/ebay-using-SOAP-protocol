package lab3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import org.json.simple.JSONObject;
import javax.jws.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import connections.ConnectionPoolManager;
import connections.dbConnection;

@WebService
public class cardvalidation {
	
	public boolean cardvalid(String user) throws Exception
	{
		
		System.out.println("1");
    //first do connection
		dbConnection connection = new dbConnection();
		Connection con=connection.config();
		System.out.println("2");
		Statement stmt=null;
		stmt=con.createStatement();
		
	
		System.out.println("3");
		JSONObject resultSet;
		System.out.println("4");
	//give first query1
		int affectedRows=0;
		int count=0;
		String query,query2,query3,query4=null;
		
		//get all elements of cart
		query="select id,quantity from cart where user='" + user + "'";
		
		try
		{
			System.out.println("3.1");
		ResultSet rs=stmt.executeQuery(query);
		System.out.println("4.1");
		System.out.println(rs);
		while(rs.next())
		{
			count++;
			System.out.println("5");
			//update all elememts in cart
			System.out.println(rs);
			double temppid = rs.getDouble(1);
            int tempqty =rs.getInt(2);
            
            System.out.println("temppid"+temppid+"tempqty"+tempqty);
            query2 = "update saleitems set quantity = (quantity-'" + tempqty + "') where id in ('" + temppid + "')";
            stmt.executeUpdate(query2);
            System.out.println("updated saleitems");
            
            query3 = "INSERT INTO userbuy (`user`, `itemdesc`, `quantity`) VALUES ('" + user + "','" + temppid + "', '" + tempqty + "')";
            stmt.executeUpdate(query3);
            System.out.println("updated userbuy");
            
           
		}
		System.out.println("4.2");
		System.out.println("done with data transcations and will now flush");
		if(count>0)
		{
			System.out.println("4.3");
			query4= "delete from cart where user ='"+user+"'";
			stmt.executeUpdate(query4);
			System.out.println("done with flush");
		}
	
		
	
		return true;
		
		
					
		}
		catch(SQLException e)
		{
			
			System.out.println("error"+e);
			return false;
		}
		
	
}
}
