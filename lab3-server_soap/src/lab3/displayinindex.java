package lab3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

//import org.json.JSONObject;
import org.json.JSONString;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import connections.ConnectionPoolManager;
import connections.dbConnection;
import connections.saleitems;

@WebService
public class displayinindex {
	
	
	@SuppressWarnings("null")
	public String displayindex()throws Exception
	{
		
		System.out.println("inside displayin index");
		System.out.println("1");
		dbConnection connection = new dbConnection();
		Connection conn=connection.config();
		
		System.out.println("2");

		JsonObject resultSet =new JsonObject();
		JsonObject obj = new JsonObject();
		//JSONObject obj1 = new JSONObject();
		
		
		
		Statement stmt = null;
		stmt = conn.createStatement();
		JsonArray jsonArray = new JsonArray();
		String sr="";

		System.out.println("3");
		int count=0;
		String getItems=null;
		List<saleitems> listinfo = new ArrayList<saleitems>();
		getItems="select * from saleitems ";
		
		try
		{
			System.out.println("4");
			ResultSet rs=stmt.executeQuery(getItems);
			resultSet=new JsonObject();
			while(rs.next())
			{
				System.out.println(rs.getInt(9));
				System.out.println(rs.getString(1));
				System.out.println(count);
				System.out.println("5");
				
			
				int id=rs.getInt(9);
				String user=rs.getString(1);
				String title=rs.getString(2);
				String itemdesc=rs.getString(3);
				String sellerinfo=rs.getString(4);
				int itemprice=rs.getInt(5);
				int quantity=rs.getInt(6);
				boolean bidding=rs.getBoolean(7);
				int timecopy=rs.getInt(8);
				
			
				saleitems item=new saleitems(id,title,itemdesc,sellerinfo,itemprice,quantity,bidding,timecopy,user);
					listinfo.add(item);
				
				
//				resultSet.addProperty("user", rs.getString(1));
//				resultSet.addProperty("title",rs.getString(2));
//				resultSet.addProperty("itemdesc", rs.getString(3));
//				resultSet.addProperty("sellerinfo", rs.getString(4));
//				resultSet.addProperty("itemprice", rs.getInt(5));
//				resultSet.addProperty("quantity", rs.getInt(6));
//				resultSet.addProperty("bidding", rs.getBoolean(7));
//				resultSet.addProperty("timecopy",rs.getInt(8));
//				resultSet.addProperty("id",rs.getInt(9));
			
				System.out.println("5.1");
				
//					jsonArray.add(resultSet);	
					count++;
			}
			
			if(count>0)
			{
				System.out.println("6");
//
//				    obj.addProperty("statusCode",new Integer(200));
//				    obj.add("userResults", jsonArray);
					
				    
				    Gson gson = new Gson();
					String json = gson.toJson(listinfo);
					System.out.println("Json string:"+json);
					 conn.close();
					 return json;
					 
				   
				    
//				    Gson gson = new Gson();
//				//	String json = gson.toJson(obj);
//					System.out.println("Json string:"+obj);
				   
			}
			else
			{
				System.out.println("7");
				
//				obj.addProperty("statusCode",new Integer(400));
//		    
//			    sr= obj.toString();
//			    conn.close();
//			    
//			    Gson gson = new Gson();
//				String json = gson.toJson(obj);
//				System.out.println("Json string:"+json);
				
			    return null;
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("8");
			System.out.println("error"+e);
	
//			
//			
//			obj.addProperty("statusCode",new Integer(400));
//
//		    
//		    sr= obj.toString();
//		    conn.close();
//		    
//		    Gson gson = new Gson();
//			String json = gson.toJson(obj);
//			System.out.println("Json string:"+json);
		    return null;
			//return something
		}
		
		}
		
		
		
		


}
