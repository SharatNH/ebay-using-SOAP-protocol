package lab3;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class calfunction {
	
		
	public String cal(String exp)throws Exception
	{
		String res="";
	    System.out.println(exp);
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	   
	    try
	    {
	    	 String foo = "40+2";
	    	  //  System.out.println(engine.eval(exp));
	    	 res=  engine.eval(exp).toString();
	    	System.out.println(res);
		    
	    }
	    catch(Exception e)
	    {
	    	 res="Invalid Input";
	    	
	    }
	    return  res;
	    
	
	}
	

}
