package connections;



public class cartitems {
	
	int id;

	String title;
	String itemdesc;

	
	int rate;
	int quantity;
	
	int total;

	
	public cartitems(int tid,String ttitle,String titemdesc,int trate,int tquantity,int ttotal)
	
	{
		id=tid;

		 title=ttitle;
		itemdesc=titemdesc;

		
		 rate=trate;
		 quantity=tquantity;
		
		 total=ttotal;
	}

}
